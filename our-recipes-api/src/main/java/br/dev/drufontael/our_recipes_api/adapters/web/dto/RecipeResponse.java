package br.dev.drufontael.our_recipes_api.adapters.web.dto;

import br.dev.drufontael.our_recipes_api.domain.model.Recipe;

public record RecipeResponse(Recipe data) {
}
