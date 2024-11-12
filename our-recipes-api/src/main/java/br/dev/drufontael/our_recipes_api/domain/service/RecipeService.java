package br.dev.drufontael.our_recipes_api.domain.service;

import br.dev.drufontael.our_recipes_api.domain.exception.ResourceNotFoundException;
import br.dev.drufontael.our_recipes_api.domain.exception.UnauthorizedUserException;
import br.dev.drufontael.our_recipes_api.domain.model.*;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageRecipePort;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceRecipePort;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceUserPort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RecipeService implements ManageRecipePort {

    private final PersistenceRecipePort persistence;
    private final PersistenceUserPort persistenceUserPort;

    public RecipeService(PersistenceRecipePort persistence, PersistenceUserPort persistenceUserPort) {
        this.persistence = persistence;
        this.persistenceUserPort = persistenceUserPort;
    }


    @Override
    public Recipe createRecipe(String name,
                               String description,
                               int servingSize,
                               int preparationTime,
                               Difficulty difficulty,
                               User author) {
        Recipe recipe = new Recipe(null, name, description, servingSize, preparationTime, difficulty);
        recipe.setAuthor(author);
        recipe = persistence.save(recipe);
        Recipe finalRecipe = recipe;
        persistenceUserPort.findByUsername(author.getUsername()).ifPresent(a -> {
            a.addRecipe(finalRecipe);
            persistenceUserPort.save(a);
        });
        return recipe;
    }

    @Override
    public void updateRecipe(Recipe recipe, User author) {
        if(recipe.getAuthor().equals(author) || author.getRoles().contains(Role.ADMIN)){
            persistence.save(recipe);
        } else throw new UnauthorizedUserException("You are not allowed to update this recipe");
    }

    @Override
    public void deleteRecipe(Recipe recipe, User author) {
        if(recipe.getAuthor().equals(author) || author.getRoles().contains(Role.ADMIN)){
            persistenceUserPort.findByUsername(author.getUsername()).ifPresent(a -> {
                a.removeRecipe(recipe);
                persistenceUserPort.save(a);
            });
            persistence.delete(recipe);
        } else throw new UnauthorizedUserException("You are not allowed to delete this recipe");
    }

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> recipes = persistence.findAll();
        Collections.sort(recipes);
        return recipes;
    }

    @Override
    public List<Recipe> getRecipes(Map<String, List<String>> filters) {
        List<Recipe> authorRecipes = getRecipes();
        if (filters.containsKey("author")) {
            User user = persistenceUserPort.findByUsername(filters.get("author").get(0)).orElse(new User());
            authorRecipes.removeIf(recipe -> !recipe.getAuthor().equals(user));
        }
        List<Tag> tags = new ArrayList<>();
        if (filters.containsKey("tags")) {
            for (String tagName : filters.get("tags")) {
                Tag tag = new Tag();
                tag.setName(tagName);
                tags.add(tag);
            }
        }
        List<Ingredient> ingredients = new ArrayList<>();
        if (filters.containsKey("ingredients")) {
            for (String ingredientName : filters.get("ingredients")) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientName);
                ingredients.add(ingredient);
            }
        }
        List<Recipe> recipeList=filterByMultipleIngredients(filterByMultipleTags(authorRecipes, tags), ingredients);
        Collections.sort(recipeList);
        return recipeList;
    }

    @Override
    public Recipe getRecipe(Long id) {
        return persistence.findById(id).orElseThrow(()->new ResourceNotFoundException("Recipe not found"));
    }

    @Override
    public void addIngredient(Long recipeId, RecipeIngredient ingredient,User author) {
        persistence.findById(recipeId).ifPresentOrElse(recipe -> {
            recipe.addIngredient(ingredient);
            updateRecipe(recipe,author);
        }, () -> {
            throw new ResourceNotFoundException("Recipe not found");
        });
    }

    @Override
    public RecipeIngredient updateIngredient(Long recipeId, RecipeIngredient ingredient,User author) {
        Recipe recipe = persistence.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("Recipe not found"));
        for (RecipeIngredient i : recipe.getIngredients()) {
            if(i.getIngredient().equals(ingredient.getIngredient())) {
                i.setQuantity(ingredient.getQuantity());
                i.setMeasurementUnit(ingredient.getMeasurementUnit());
                updateRecipe(recipe,author);
                return i;
            }
        }
        throw new ResourceNotFoundException("Ingredient not found");
    }

    @Override
    public void removeIngredient(Long recipeId, RecipeIngredient ingredient,User author) {
        Recipe recipe = persistence.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("Recipe not found"));
        for (RecipeIngredient i : recipe.getIngredients()) {
            if(i.getIngredient().equals(ingredient.getIngredient())) {
                recipe.getIngredients().remove(i);
                updateRecipe(recipe,author);
                return;
            }
        }
        throw new ResourceNotFoundException("Ingredient not found");

    }

    @Override
    public void addStep(Long recipeId, Step step,User author) {
        Recipe recipe = persistence.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("Recipe not found"));
        recipe.addStep(step);
        updateRecipe(recipe,author);
    }

    @Override
    public Step updateStep(Long recipeId, Step step,User author) {
        Recipe recipe = persistence.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("Recipe not found"));
        for (Step s : recipe.getSteps()) {
            if(s.getStepNumber() == step.getStepNumber()) {
                s.setDescription(step.getDescription());
                updateRecipe(recipe,author);
                return s;
            }
        }
        return null;
    }

    @Override
    public void removeStep(Long recipeId, Step step,User author) {
        Recipe recipe = persistence.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("Recipe not found"));
        for (Step s : recipe.getSteps()) {
            if(s.getStepNumber() == step.getStepNumber()) {
                recipe.getSteps().remove(s);
                updateRecipe(recipe,author);
                return;
            }
        }
        throw new ResourceNotFoundException("Step not found");
    }

    @Override
    public void addTag(Long recipeId, Tag tag,User author) {
        Recipe recipe = persistence.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("Recipe not found"));
        recipe.addTag(tag);
        updateRecipe(recipe,author);

    }

    @Override
    public void removeTag(Long recipeId, Tag tag,User author) {
        Recipe recipe = persistence.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("Recipe not found"));
        for (Tag t : recipe.getTags()) {
            if(t.getName().equals(tag.getName())) {
                recipe.getTags().remove(t);
                updateRecipe(recipe,author);
                return;
            }
        }
        throw new ResourceNotFoundException("Tag not found");

    }

    @Override
    public void addReview(Long recipeId, Review review,User author) {
        Recipe recipe = persistence.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("Recipe not found"));
        review.setUser(author);
        recipe.addReview(review);
        persistence.save(recipe);

    }

    @Override
    public Review updateReview(Long recipeId, Review review,User author) {
        Recipe recipe = persistence.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("Recipe not found"));
        for (Review r : recipe.getReviews()) {
            if((r.getUser().equals(author) || author.getRoles().contains(Role.ADMIN)) && r.getId() == review.getId()) {
                r.setRating(review.getRating());
                r.setComment(review.getComment());
                persistence.save(recipe);
                return r;
            }
        }
        throw new ResourceNotFoundException("Review not found");
    }

    @Override
    public void removeReview(Long recipeId, Review review,User author) {
        Recipe recipe = persistence.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("Recipe not found"));
        for (Review r : recipe.getReviews()) {
            if((r.getUser().equals(author) || author.getRoles().contains(Role.ADMIN)) && r.getId() == review.getId()) {
                recipe.getReviews().remove(r);
                persistence.save(recipe);
                return;
            }
        }
        throw new ResourceNotFoundException("Review not found");
    }

    private List<Recipe> filterByOneTag(List<Recipe> recipes, Tag tag) {
        List<Recipe> targetList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getTags().contains(tag)) {
                targetList.add(recipe);
            }
        }
        return targetList;
    }

    private List<Recipe> filterByMultipleTags(List<Recipe> recipes, List<Tag> tags) {
        if(tags.isEmpty()) return recipes;
        if(tags.size()==1) return filterByOneTag(recipes, tags.get(0));
        List<Recipe> targetList = filterByOneTag(recipes, tags.get(0));
        tags.remove(0);
        return filterByMultipleTags(targetList, tags);
    }

    private List<Recipe> filterByOneIngredient(List<Recipe> recipes, Ingredient ingredient) {
        List<Recipe> targetList = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient)) {
                targetList.add(recipe);
            }
        }
        return targetList;
    }

    private List<Recipe> filterByMultipleIngredients(List<Recipe> recipes, List<Ingredient> ingredients) {
        if(ingredients.isEmpty()) return recipes;
        if(ingredients.size()==1) return filterByOneIngredient(recipes, ingredients.get(0));
        List<Recipe> targetList = filterByOneIngredient(recipes, ingredients.get(0));
        ingredients.remove(0);
        return filterByMultipleIngredients(targetList, ingredients);
    }

}
