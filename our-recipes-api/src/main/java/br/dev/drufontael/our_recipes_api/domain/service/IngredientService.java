package br.dev.drufontael.our_recipes_api.domain.service;

import br.dev.drufontael.our_recipes_api.domain.exception.ResourceAlreadyExistsException;
import br.dev.drufontael.our_recipes_api.domain.exception.ResourceNotFoundException;
import br.dev.drufontael.our_recipes_api.domain.model.Ingredient;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageIngredientPort;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceIngredientPort;

import java.util.ArrayList;
import java.util.List;

public class IngredientService implements ManageIngredientPort {

    private final PersistenceIngredientPort persistence;

    public IngredientService(PersistenceIngredientPort persistence) {
        this.persistence = persistence;
    }
    @Override
    public void register(Ingredient ingredient) {
        for(Ingredient i:findByName(ingredient.getName())) {
            if(i.getName().equals(ingredient.getName())) throw new ResourceAlreadyExistsException("Ingredient already exists");
        }
        persistence.save(ingredient);
    }

    @Override
    public void register(Ingredient[] ingredients) {
        persistence.save(ingredients);
    }

    @Override
    public void register(String[] ingredientsNames) {
        for (String name : ingredientsNames) {
            persistence.save(new Ingredient(null, name, ""));
        }
    }

    @Override
    public void update(Ingredient ingredient) {
        persistence.findById(ingredient.getId()).orElseThrow(()->new ResourceNotFoundException("Ingredient not found"));
        persistence.save(ingredient);
    }

    @Override
    public void delete(Ingredient ingredient) {
        persistence.findById(ingredient.getId()).orElseThrow(()->new ResourceNotFoundException("Ingredient not found"));
        persistence.delete(ingredient);
    }

    @Override
    public List<Ingredient> getAll() {
        return persistence.findAll();
    }

    @Override
    public Ingredient findById(Long id) {
        return persistence.findById(id).orElseThrow(()->new ResourceNotFoundException("Ingredient not found"));
    }

    @Override
    public List<Ingredient> findByName(String name) {
        List<Ingredient> targetList=new ArrayList<>();
        for(Ingredient i:persistence.findAll()) {
            if(i.getName().toLowerCase().contains(name.toLowerCase())) targetList.add(i);
        }
        return targetList;
    }
}
