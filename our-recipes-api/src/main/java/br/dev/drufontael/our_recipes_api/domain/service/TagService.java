package br.dev.drufontael.our_recipes_api.domain.service;

import br.dev.drufontael.our_recipes_api.domain.exception.ResourceAlreadyExistsException;
import br.dev.drufontael.our_recipes_api.domain.exception.ResourceNotFoundException;
import br.dev.drufontael.our_recipes_api.domain.model.Tag;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageTagPort;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceTagPort;

import java.util.List;

public class TagService implements ManageTagPort {

    private final PersistenceTagPort persistence;

    public TagService(PersistenceTagPort persistence) {
        this.persistence = persistence;
    }

    @Override
    public Tag register(Tag tag) {
        persistence.findByName(tag.getName()).ifPresent(tagPresent ->{
            throw new ResourceAlreadyExistsException("Tag already exists");
        });
        return persistence.save(tag);
    }

    @Override
    public void register(String[] tagsNames) {
        for (String tag : tagsNames) {
            persistence.save(new Tag(tag));
        }
    }

    @Override
    public void update(Tag tag) {
        persistence.findByName(tag.getName()).ifPresentOrElse(persistence::save,
                ()->{throw new ResourceNotFoundException("Tag not found");});

    }

    @Override
    public void delete(Tag tag) {
        persistence.findByName(tag.getName()).ifPresentOrElse(persistence::delete,
                ()->{throw new ResourceNotFoundException("Tag not found");});
    }

    @Override
    public Tag findByName(String name) {
        return persistence.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Tag not found"));
    }

    @Override
    public List<Tag> findAll() {
        return persistence.findAll();
    }
}
