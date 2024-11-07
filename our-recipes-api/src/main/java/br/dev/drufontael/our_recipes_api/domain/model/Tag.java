package br.dev.drufontael.our_recipes_api.domain.model;

import java.util.HashSet;
import java.util.Set;

public class Tag {
    private String name;
    private Set<Recipe> recipes = new HashSet<Recipe>();//TODO: Verificar se é necessário

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }
}
