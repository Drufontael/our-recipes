package br.dev.drufontael.our_recipes_api.domain.ports.out;

import br.dev.drufontael.our_recipes_api.domain.model.Tag;

import java.util.List;
import java.util.Optional;

public interface PersistenceTagPort {
    Tag save(Tag tag);
    void delete(Tag tag);
    Optional<Tag> findByName(String name);
    List<Tag> findAll();
}
