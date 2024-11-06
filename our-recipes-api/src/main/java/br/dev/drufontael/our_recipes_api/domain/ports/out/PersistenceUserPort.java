package br.dev.drufontael.our_recipes_api.domain.ports.out;

import br.dev.drufontael.our_recipes_api.domain.model.User;

import java.util.Optional;

public interface PersistenceUserPort {

    void save(User user);
    Optional<User> findByUsername(String username);
    void delete(User user);
}
