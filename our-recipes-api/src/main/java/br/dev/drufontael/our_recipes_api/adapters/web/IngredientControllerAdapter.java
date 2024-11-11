package br.dev.drufontael.our_recipes_api.adapters.web;

import br.dev.drufontael.our_recipes_api.adapters.web.dto.IngredientRequest;
import br.dev.drufontael.our_recipes_api.adapters.web.dto.IngredientResponse;
import br.dev.drufontael.our_recipes_api.domain.model.Ingredient;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageIngredientPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/ingredients")
@AllArgsConstructor
public class IngredientControllerAdapter {

    private final ManageIngredientPort manageIngredientPort;

    @PostMapping
    public ResponseEntity<IngredientResponse> register(@RequestBody IngredientRequest request){
        Ingredient ingredient = manageIngredientPort.register(request.toDomain());
        return ResponseEntity.ok(new IngredientResponse(ingredient.getId(),ingredient.getName(),ingredient.getDescription()));
    }

    @PostMapping("/all")
    public ResponseEntity<Void> register(@RequestBody List<String> names){
        manageIngredientPort.register(names.toArray(new String[0]));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<IngredientResponse>> getAll(){
        List<Ingredient> ingredients = manageIngredientPort.getAll();
        return ResponseEntity.ok(ingredients.stream().map(ingredient ->
            new IngredientResponse(ingredient.getId(),ingredient.getName(),ingredient.getDescription())).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponse> getById(@PathVariable Long id){
        Ingredient ingredient = manageIngredientPort.findById(id);
        return ResponseEntity.ok(new IngredientResponse(ingredient.getId(),ingredient.getName(),ingredient.getDescription()));
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<List<IngredientResponse>> getByName(@PathVariable String name){
        return ResponseEntity.ok(manageIngredientPort.findByName(name).stream().map(ingredient ->
            new IngredientResponse(ingredient.getId(),ingredient.getName(),ingredient.getDescription())).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Ingredient ingredient = manageIngredientPort.findById(id);
        manageIngredientPort.delete(ingredient);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientResponse> update(@PathVariable Long id, @RequestBody IngredientRequest request){
        Ingredient ingredient= manageIngredientPort.findById(id);
        ingredient.setName(request.name());
        ingredient.setDescription(request.description());
        return ResponseEntity.ok(new IngredientResponse(ingredient.getId(),ingredient.getName(),ingredient.getDescription()));
    }

}
