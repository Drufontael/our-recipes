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
    void updateRecipe(Recipe recipe,User author);
    void deleteRecipe(Recipe recipe,User author);
    List<Recipe> getRecipes();
    List<Recipe> getRecipes(Map<String,String> filters);
    Recipe getRecipe(Long id);


    //
    // Manipulação de ingredientes
    //
    void addIngredient(Recipe recipe, RecipeIngredient ingredient);
    RecipeIngredient updateIngredient(Recipe recipe, RecipeIngredient ingredient);
    void removeIngredient(Recipe recipe, RecipeIngredient ingredient);

    //
    // Manipulação de passos
    //
    void addStep(Recipe recipe, Step step);
    Step updateStep(Recipe recipe, Step step);
    void removeStep(Recipe recipe, Step step);

    //
    // Manipulação de tags
    //
    void addTag(Recipe recipe, Tag tag);
    void removeTag(Recipe recipe, Tag tag);

    //
    // Manipulação de avaliações
    //
    void addReview(Recipe recipe, Review review);
    Review updateReview(Recipe recipe, Review review);
    void removeReview(Recipe recipe, Review review);

}
