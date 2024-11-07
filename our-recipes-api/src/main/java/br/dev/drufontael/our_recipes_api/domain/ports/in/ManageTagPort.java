package br.dev.drufontael.our_recipes_api.domain.ports.in;

import br.dev.drufontael.our_recipes_api.domain.model.Tag;

import java.util.List;

public interface ManageTagPort {
    Tag register(Tag tag);
    void register(String[] tagsNames);
    void update(Tag tag);
    void delete(Tag tag);
    Tag findByName(String name);
    List<Tag> findAll();    
}
