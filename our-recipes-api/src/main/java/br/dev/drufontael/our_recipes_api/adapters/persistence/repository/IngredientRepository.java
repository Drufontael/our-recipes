package br.dev.drufontael.our_recipes_api.adapters.persistence.repository;

import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<IngredientsEntity, Long> {
}
