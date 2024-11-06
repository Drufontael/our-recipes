package br.dev.drufontael.our_recipes_api.domain.ports.in;

import br.dev.drufontael.our_recipes_api.domain.model.Role;
import br.dev.drufontael.our_recipes_api.domain.model.User;

public interface ManageUserPort {
    void register(User user);
    void update(User user);
    void delete(User user);
    void addRole(User user, Role role);
    User findByUsername(String username);
    Boolean logar(String username, String password);



}
