package br.dev.drufontael.our_recipes_api.adapters.persistence;

import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.UserRepository;
import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities.UserEntity;
import br.dev.drufontael.our_recipes_api.domain.model.User;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceUserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserPersistenceAdapter implements PersistenceUserPort {

    private final UserRepository repository;


    @Override
    public User save(User user) {
        UserEntity userEntity= new UserEntity(user);
        return repository.save(userEntity).toDomain();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserEntity> userEntity = repository.findByUsername(username);
        if (userEntity.isPresent()) {
            return Optional.of(userEntity.get().toDomain());
        }
        return Optional.empty();
    }

    @Override
    public void delete(User user) {
        UserEntity userEntity= new UserEntity(user);
        repository.delete(userEntity);
    }
}
