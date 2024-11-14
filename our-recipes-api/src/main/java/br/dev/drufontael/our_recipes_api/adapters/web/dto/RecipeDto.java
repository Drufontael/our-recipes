package br.dev.drufontael.our_recipes_api.adapters.web.dto;

import br.dev.drufontael.our_recipes_api.domain.model.*;

import java.util.List;
import java.util.Set;

public record RecipeDto(Long id,
                        String name,
                        String description,
                        int servingSize,
                        int preparationTime,
                        String difficulty,
                        Set<Tag> tags,
                        Set<RecipeIngredient> ingredients,
                        List<Step> steps,
                        List<ReviewDto> reviews,
                        Double rating,
                        String author) {

    public Recipe toDomain() {
        Recipe recipe = new Recipe(id, name, description, servingSize, preparationTime, Difficulty.valueOf(difficulty));
        recipe.getTags().addAll(tags);
        recipe.getIngredients().addAll(ingredients);
        recipe.getSteps().addAll(steps);
        recipe.getReviews().addAll(reviews.stream().map(ReviewDto::toDomain).toList());
        return recipe;
    }

    public static RecipeDto fromDomain(Recipe recipe) {
        return new RecipeDto(recipe.getId(),
                             recipe.getName(),
                             recipe.getDescription(),
                             recipe.getServingSize(),
                             recipe.getPreparationTime(),
                             recipe.getDifficulty().name(),
                             recipe.getTags(),
                             recipe.getIngredients(),
                             recipe.getSteps(),
                             recipe.getReviews().stream().map(ReviewDto::fromDomain).toList(),
                             recipe.getRating(),
                             recipe.getAuthor().getUsername());
    }
}
