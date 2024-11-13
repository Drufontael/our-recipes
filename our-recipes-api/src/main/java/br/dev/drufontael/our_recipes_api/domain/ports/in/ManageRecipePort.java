package br.dev.drufontael.our_recipes_api.domain.ports.in;

import br.dev.drufontael.our_recipes_api.domain.model.*;

import java.util.List;
import java.util.Map;

public interface ManageRecipePort {

    //
    // Manipulação macro de receitas
    //
    Recipe createRecipe(String name,
                        String description,
                        int servingSize,
                        int preparationTime,
                        Difficulty difficulty,
                        User author);
    Recipe updateRecipe(Long id,Recipe recipe,User author);
    void deleteRecipe(Recipe recipe,User author);
    List<Recipe> getRecipes();
    List<Recipe> getRecipes(Map<String,List<String>> filters);
    Recipe getRecipe(Long id);


    //
    // Manipulação de ingredientes
    //
    void addIngredient(Long recipeId, RecipeIngredient ingredient,User author);
    RecipeIngredient updateIngredient(Long recipeId, RecipeIngredient ingredient,User author);
    void removeIngredient(Long recipeId, RecipeIngredient ingredient,User author);

    //
    // Manipulação de passos
    //
    void addStep(Long recipeId, Step step,User author);
    Step updateStep(Long recipeId, Step step,User author);
    void removeStep(Long recipeId, Step step,User author);

    //
    // Manipulação de tags
    //
    void addTag(Long recipeId, Tag tag,User author);
    void removeTag(Long recipeId, Tag tag,User author);

    //
    // Manipulação de avaliações
    //
    void addReview(Long recipeId, Review review,User author);
    Review updateReview(Long recipeId, Review review,User author);
    void removeReview(Long recipeId, Review review,User author);

}
