package br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities;

import br.dev.drufontael.our_recipes_api.domain.model.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name="tb_recipe")
@Data
@NoArgsConstructor
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    private String description;
    private int servingSize;
    private int preparationTime;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<RecipeTagEntity> tags = new HashSet<>();
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<RecipeIngredientEntity> ingredients = new HashSet<>();
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<StepEntity> steps = new ArrayList<>();
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ReviewEntity> reviews = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    public RecipeEntity(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.description = recipe.getDescription();
        this.servingSize = recipe.getServingSize();
        this.preparationTime = recipe.getPreparationTime();
        this.difficulty = recipe.getDifficulty();
        this.tags.addAll(recipe.getTags().stream().map(tag -> new RecipeTagEntity(tag, recipe)).toList());
        this.ingredients.addAll(recipe.getIngredients().stream()
                .map(recipeIngredient -> new RecipeIngredientEntity(recipeIngredient, recipe)).toList());
        this.steps.addAll(recipe.getSteps().stream().map(step -> new StepEntity(step, recipe)).toList());
        this.reviews.addAll(recipe.getReviews().stream().map(ReviewEntity::new).toList());
        this.author = new UserEntity(recipe.getAuthor());
    }

    public Recipe toDomain() {
        Recipe recipe = new Recipe(this.id, this.name, this.description, this.servingSize, this.preparationTime, this.difficulty);
        recipe.getTags().addAll(this.tags.stream().map(RecipeTagEntity::toDomain).toList());
        recipe.getIngredients().addAll(this.ingredients.stream().map(RecipeIngredientEntity::toDomain).toList());
        recipe.getSteps().addAll(this.steps.stream().map(StepEntity::toDomain).toList());
        recipe.getReviews().addAll(this.reviews.stream().map(ReviewEntity::toDomain).toList());
        recipe.setAuthor(this.author.toDomain());
        return recipe;
    }
}
