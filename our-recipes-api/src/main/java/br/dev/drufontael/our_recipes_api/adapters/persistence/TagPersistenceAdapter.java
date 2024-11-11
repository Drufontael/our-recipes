package br.dev.drufontael.our_recipes_api.adapters.persistence;

import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.TagRepository;
import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities.TagEntity;
import br.dev.drufontael.our_recipes_api.domain.model.Tag;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceTagPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagPersistenceAdapter implements PersistenceTagPort {

    private final TagRepository repository;

    @Override
    public Tag save(Tag tag) {
        return repository.save(new TagEntity(tag)).toDomain();
    }

    @Override
    public void delete(Tag tag) {
        repository.delete(new TagEntity(tag));
    }

    @Override
    public Optional<Tag> findByName(String name) {
        Optional<TagEntity> entity = repository.findById(name);
        if (entity.isPresent()) {
            return Optional.of(entity.get().toDomain());
        }
        return Optional.empty();
    }

    @Override
    public List<Tag> findAll() {
        return repository.findAll().stream().map(TagEntity::toDomain).toList();
    }
}
