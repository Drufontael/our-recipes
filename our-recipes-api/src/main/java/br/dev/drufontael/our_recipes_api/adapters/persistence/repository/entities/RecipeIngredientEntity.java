package br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities;

import br.dev.drufontael.our_recipes_api.domain.model.Recipe;
import br.dev.drufontael.our_recipes_api.domain.model.RecipeIngredient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_recipe_ingredient")
@Data
@NoArgsConstructor
public class RecipeIngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private RecipeEntity recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private IngredientsEntity ingredient;

    private double quantity;

    @ManyToOne
    @JoinColumn(name = "measurement_unit_id")
    private MeasurementUnitEntity measurementUnit;

    public RecipeIngredientEntity(RecipeIngredient recipeIngredient, Recipe recipe) {
        this.recipe = new RecipeEntity(recipe);
        this.ingredient = new IngredientsEntity(recipeIngredient.getIngredient());
        this.quantity = recipeIngredient.getQuantity();
        this.measurementUnit = new MeasurementUnitEntity(recipeIngredient.getMeasurementUnit());
    }



    public RecipeIngredient toDomain() {
        return new RecipeIngredient(this.ingredient.toDomain(), this.quantity, this.measurementUnit.toDomain());
    }
}
