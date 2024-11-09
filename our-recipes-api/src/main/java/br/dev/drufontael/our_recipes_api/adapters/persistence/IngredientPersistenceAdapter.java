package br.dev.drufontael.our_recipes_api.adapters.persistence;

import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.IngredientRepository;
import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities.IngredientsEntity;
import br.dev.drufontael.our_recipes_api.domain.exception.ResourceNotFoundException;
import br.dev.drufontael.our_recipes_api.domain.model.Ingredient;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceIngredientPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IngredientPersistenceAdapter implements PersistenceIngredientPort {

    private final IngredientRepository repository;

    @Override
    public Ingredient save(Ingredient ingredient) {
        return repository.save(new IngredientsEntity(ingredient)).toDomain();
    }

    @Override
    public void save(Ingredient[] ingredients) {
        List<IngredientsEntity> entities = List.of(ingredients).stream().map(IngredientsEntity::new).toList();
        repository.saveAll(entities);
    }

    @Override
    public void delete(Ingredient ingredient) {
        repository.delete(new IngredientsEntity(ingredient));
    }

    @Override
    public void update(Ingredient ingredient) {
        findById(ingredient.getId()).ifPresentOrElse(i -> repository.save(new IngredientsEntity(ingredient)), () -> {
            throw new ResourceNotFoundException("Ingredient not found");
        });

    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        Optional<IngredientsEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            return Optional.of(entity.get().toDomain());
        }
        return Optional.empty();
    }

    @Override
    public List<Ingredient> findAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        return repository.findAll(sort).stream().map(IngredientsEntity::toDomain).toList();
    }
}
