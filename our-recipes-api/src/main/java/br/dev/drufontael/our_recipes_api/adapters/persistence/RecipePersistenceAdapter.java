package br.dev.drufontael.our_recipes_api.adapters.persistence;

import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.RecipeRepository;
import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities.RecipeEntity;
import br.dev.drufontael.our_recipes_api.domain.exception.ResourceNotFoundException;
import br.dev.drufontael.our_recipes_api.domain.model.Recipe;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceRecipePort;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipePersistenceAdapter implements PersistenceRecipePort {

    private final RecipeRepository repository;
    @Override
    @Transactional
    public Recipe save(Recipe recipe) {
        return repository.save(new RecipeEntity(recipe)).toDomain();
    }

    @Override
    @Transactional
    public void delete(Recipe recipe) {
        RecipeEntity recipeEntity = repository.findById(recipe.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found"));
        recipeEntity.setActive(false);
        repository.save(recipeEntity);
    }

    @Override
    @Transactional
    public Recipe update(Recipe recipe) {
        return repository.findById(recipe.getId()).map(entity -> repository.save(new RecipeEntity(recipe)).toDomain())
                .orElseThrow(()-> new ResourceNotFoundException("Recipe not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Recipe> findById(Long id) {
        Optional<RecipeEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            return Optional.of(entity.get().toDomain());
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Recipe> findAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "rating");
        return repository.findAllByActiveTrue(sort).stream().map(RecipeEntity::toDomain).sorted().toList();
    }
}
