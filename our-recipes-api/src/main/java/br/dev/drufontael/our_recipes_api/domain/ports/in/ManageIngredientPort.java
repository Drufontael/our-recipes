package br.dev.drufontael.our_recipes_api.domain.ports.in;

import br.dev.drufontael.our_recipes_api.domain.model.Ingredient;

import java.util.List;

public interface ManageIngredientPort {

    void register(Ingredient ingredient);
    void register(Ingredient[] ingredients);
    void register(String[] ingredientsNames);
    void update(Ingredient ingredient);
    void delete(Ingredient ingredient);
    List<Ingredient> getAll();
    Ingredient findById(Long id);
    List<Ingredient> findByName(String name);
}

