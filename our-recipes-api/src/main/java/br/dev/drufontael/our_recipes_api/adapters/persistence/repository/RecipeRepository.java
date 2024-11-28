package br.dev.drufontael.our_recipes_api.adapters.persistence.repository;

import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    List<RecipeEntity> findAllByActiveTrue();
}
