package br.dev.drufontael.our_recipes_api.domain.ports.out;

import br.dev.drufontael.our_recipes_api.domain.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface PersistenceRecipePort {
    Recipe save(Recipe recipe);
    void delete(Recipe recipe);
    Recipe update(Recipe recipe);
    Optional<Recipe> findById(Long id);
    List<Recipe> findAll();
}
