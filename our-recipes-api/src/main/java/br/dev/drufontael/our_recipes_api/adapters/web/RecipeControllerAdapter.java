package br.dev.drufontael.our_recipes_api.adapters.web;

import br.dev.drufontael.our_recipes_api.adapters.web.dto.*;
import br.dev.drufontael.our_recipes_api.domain.model.*;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageIngredientPort;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageMeasurementUnitPort;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageRecipePort;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageUserPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("v1/api/recipes")
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class RecipeControllerAdapter {

    private final ManageRecipePort manageRecipePort;
    private final ManageUserPort manageUserPort;
    private final ManageIngredientPort manageIngredientPort;
    private final ManageMeasurementUnitPort manageMeasurementUnitPort;

    // CRUD Receitas

    @PostMapping
    public ResponseEntity<RecipeDto> addRecipe(@RequestBody RecipeCreateRequest request){
        Recipe recipe = manageRecipePort.createRecipe(request.name(),
                request.description(),
                request.servingSize(),
                request.preparationTime(),
                Difficulty.valueOf(request.difficulty()),
                getAuthenticatedUser());
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    @GetMapping
    public ResponseEntity<List<RecipeSummaryResponse>> getAll() {
        List<Recipe> recipes = manageRecipePort.getRecipes();
        return ResponseEntity.ok(recipes.stream().map(RecipeSummaryResponse::fromDomain).toList());
    }

    @PostMapping("/filter")
    public ResponseEntity<List<RecipeSummaryResponse>> filter(@RequestBody Filters filters) {
        List<Recipe> recipes = manageRecipePort.getRecipes(filters.getFilters());
        return ResponseEntity.ok(recipes.stream().map(RecipeSummaryResponse::fromDomain).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable Long id) {
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    @GetMapping("difficulties")
    public ResponseEntity<List<String>> getDifficulties() {
        return ResponseEntity.ok(Arrays.stream(Difficulty.values()).map(Difficulty::name).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable Long id, @RequestBody RecipeDto request) {
        Recipe recipe = request.toDomain();
        recipe.setAuthor(getAuthenticatedUser());
        for (Review r:recipe.getReviews()){
            if(r.getUser().getId() == null){
                User user=manageUserPort.findByUsername(r.getUser().getUsername());
                r.setUser(user);
            }
        }
        recipe = manageRecipePort.updateRecipe(id,recipe, getAuthenticatedUser());
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        Recipe recipe = manageRecipePort.getRecipe(id);
        manageRecipePort.deleteRecipe(recipe, getAuthenticatedUser());
        return ResponseEntity.noContent().build();
    }


    // Manipulação de ingredientes

    @PostMapping("/{id}/ingredients")
    public ResponseEntity<RecipeDto> addIngredient(@PathVariable Long id, @RequestBody RecipeIngredientDto request) {
        manageRecipePort.addIngredient(id, recipeIngredientToDomain(request), getAuthenticatedUser());
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    @PutMapping("/{id}/ingredients")
    public ResponseEntity<RecipeDto> updateIngredient(@PathVariable Long id, @RequestBody RecipeIngredientDto request) {
        manageRecipePort.updateIngredient(id, recipeIngredientToDomain(request), getAuthenticatedUser());
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    @DeleteMapping("/{id}/ingredients")
    public ResponseEntity<RecipeDto> deleteIngredient(@PathVariable Long id, @RequestBody RecipeIngredientDto request) {
        manageRecipePort.removeIngredient(id, recipeIngredientToDomain(request), getAuthenticatedUser());
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    //Manipulação de Passos


    @PostMapping("/{id}/steps")
    public ResponseEntity<RecipeDto> addStep(@PathVariable Long id, @RequestBody StepDto request) {
        manageRecipePort.addStep(id, new Step(request.stepNumber(), request.description()), getAuthenticatedUser());
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    @PutMapping("/{id}/steps")
    public ResponseEntity<RecipeDto> updateStep(@PathVariable Long id, @RequestBody StepDto request) {
        manageRecipePort.updateStep(id, new Step(request.stepNumber(), request.description()), getAuthenticatedUser());
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    @DeleteMapping("/{id}/steps")
    public ResponseEntity<RecipeDto> deleteStep(@PathVariable Long id, @RequestBody StepDto request) {
        manageRecipePort.removeStep(id, new Step(request.stepNumber(),request.description()), getAuthenticatedUser());
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    //Manipulação de Tag's

    @PostMapping("/{id}/tags")
    public ResponseEntity<RecipeDto> addTag(@PathVariable Long id, @RequestBody TagDto request) {
        manageRecipePort.addTag(id, request.toDomain(), getAuthenticatedUser());
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }


    @DeleteMapping("/{id}/tags")
    public ResponseEntity<RecipeDto> deleteTag(@PathVariable Long id, @RequestBody TagDto request) {
        manageRecipePort.removeTag(id, request.toDomain(), getAuthenticatedUser());
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    //Manipulação de avaliações

    @PostMapping("/{id}/reviews")
    public ResponseEntity<RecipeDto> addReview(@PathVariable Long id, @RequestBody ReviewDto request) {
        manageRecipePort.addReview(id, request.toDomain(), getAuthenticatedUser());
        log.info("Recebendo avaliação: {}", request);
        log.info("Usuário autenticado: {}", getAuthenticatedUser().getUsername());
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    @PutMapping("/{id}/reviews")
    public ResponseEntity<RecipeDto> updateReview(@PathVariable Long id, @RequestBody ReviewDto request) {
        manageRecipePort.updateReview(id, request.toDomain(), getAuthenticatedUser());
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }

    @DeleteMapping("/{id}/reviews")
    public ResponseEntity<RecipeDto> deleteReview(@PathVariable Long id, @RequestBody ReviewDto request) {
        manageRecipePort.removeReview(id, request.toDomain(), getAuthenticatedUser());
        Recipe recipe = manageRecipePort.getRecipe(id);
        return ResponseEntity.ok(RecipeDto.fromDomain(recipe));
    }









    private User getAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()){
            return manageUserPort.findByUsername(authentication.getName());
        }
        return null;
    }

    private RecipeIngredient recipeIngredientToDomain(RecipeIngredientDto request) {
        return new RecipeIngredient(manageIngredientPort.findById(request.ingredientId()),
                request.quantity(),
                manageMeasurementUnitPort.findById(request.measurementUnitId()));
    }
}
