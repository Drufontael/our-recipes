package br.dev.drufontael.our_recipes_api.adapters.web.dto;

import br.dev.drufontael.our_recipes_api.domain.model.User;

public record UserRequest(String username, String password, String email) {
    public User toDomain() {
        return new User(null, username, password, email);
    }
}
