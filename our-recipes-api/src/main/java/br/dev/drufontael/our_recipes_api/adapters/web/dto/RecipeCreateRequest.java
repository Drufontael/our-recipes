package br.dev.drufontael.our_recipes_api.adapters.web.dto;

public record RecipeCreateRequest(String name,
                                  String description,
                                  int servingSize,
                                  int preparationTime,
                                  String difficulty) {

}
