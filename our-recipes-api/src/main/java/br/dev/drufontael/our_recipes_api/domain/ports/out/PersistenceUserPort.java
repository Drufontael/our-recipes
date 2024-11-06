package br.dev.drufontael.our_recipes_api.domain.ports.out;

import br.dev.drufontael.our_recipes_api.domain.model.User;

public interface PersistenceUserPort {

    void save(User user);
    User findByUsername(String username);
    void delete(User user);
}
