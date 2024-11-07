package br.dev.drufontael.our_recipes_api.domain.model;

public class RecipeIngredient implements Comparable<RecipeIngredient> {
    private Ingredient ingredient;
    private double quantity;
    private MeasurementUnit measurementUnit;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredient, double quantity, MeasurementUnit measurementUnit) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.measurementUnit = measurementUnit;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeIngredient that = (RecipeIngredient) o;
        return ingredient.equals(that.ingredient);
    }

    @Override
    public int hashCode() {
        return ingredient.hashCode();
    }

    @Override
    public int compareTo(RecipeIngredient o) {
        return this.ingredient.compareTo(o.ingredient);
    }
}
