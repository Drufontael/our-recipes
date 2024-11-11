package br.dev.drufontael.our_recipes_api.adapters.web.dto;

import br.dev.drufontael.our_recipes_api.domain.model.Tag;

public record TagDto(String name, String description) {

    public Tag toDomain() {
        Tag tag = new Tag(name);
        tag.setDescription(description);
        return tag;
    }
}
