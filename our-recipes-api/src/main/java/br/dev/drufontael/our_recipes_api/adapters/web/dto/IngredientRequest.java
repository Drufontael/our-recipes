package br.dev.drufontael.our_recipes_api.adapters.web.dto;

import br.dev.drufontael.our_recipes_api.domain.model.Ingredient;

public record IngredientRequest(String name, String description) {
    public Ingredient toDomain() {
        return new Ingredient(null, name, description);
    }
}
