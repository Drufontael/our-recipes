package br.dev.drufontael.our_recipes_api.adapters.web.dto;

import br.dev.drufontael.our_recipes_api.domain.model.Recipe;
import br.dev.drufontael.our_recipes_api.domain.model.Tag;

import java.util.Set;

public record RecipeSummaryResponse(Long id,
                                    String name,
                                    String description,
                                    Double rating,
                                    Set<Tag> tags) {

    public static RecipeSummaryResponse fromDomain(Recipe recipe) {
        return new RecipeSummaryResponse(recipe.getId(), recipe.getName(), recipe.getDescription(), recipe.getRating(), recipe.getTags());
    }
}
