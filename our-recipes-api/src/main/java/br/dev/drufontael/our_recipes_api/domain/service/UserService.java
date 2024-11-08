package br.dev.drufontael.our_recipes_api.domain.service;

import br.dev.drufontael.our_recipes_api.domain.exception.UserAlreadyExistsException;
import br.dev.drufontael.our_recipes_api.domain.exception.UserNotFoundException;
import br.dev.drufontael.our_recipes_api.domain.model.Role;
import br.dev.drufontael.our_recipes_api.domain.model.User;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageUserPort;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceUserPort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService implements ManageUserPort {

    private final PersistenceUserPort persistence;

    public UserService(PersistenceUserPort persistence) {
        this.persistence = persistence;
    }
    @Override
    public User register(User user, PasswordEncoder encoder) {
        if (persistence.findByUsername(user.getUsername()).isPresent()) throw new UserAlreadyExistsException();
        user.setPassword(encoder.encode(user.getPassword()));
        user.addRole(Role.USER);
        return persistence.save(user);
    }

    @Override
    public void update(User user) {
        persistence.findByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new);
        persistence.save(user);
    }

    @Override
    public void delete(User user) {
        persistence.findByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new);
        persistence.delete(user);
    }

    @Override
    public void addRole(User user, Role role) {
        persistence.findByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new);
        user.addRole(role);
        persistence.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return persistence.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }
}
