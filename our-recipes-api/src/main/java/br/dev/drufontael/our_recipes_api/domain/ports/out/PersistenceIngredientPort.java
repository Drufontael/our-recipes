package br.dev.drufontael.our_recipes_api.domain.ports.out;

import br.dev.drufontael.our_recipes_api.domain.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface PersistenceIngredientPort {

    void save(Ingredient ingredient);
    void save(Ingredient[] ingredients);
    void delete(Ingredient ingredient);
    void update(Ingredient ingredient);
    Optional<Ingredient> findById(Long id);
    List<Ingredient> findAll();

}
