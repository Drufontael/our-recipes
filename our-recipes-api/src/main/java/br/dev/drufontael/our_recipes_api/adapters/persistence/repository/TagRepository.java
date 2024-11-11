package br.dev.drufontael.our_recipes_api.adapters.persistence.repository;

import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, String> {
}
