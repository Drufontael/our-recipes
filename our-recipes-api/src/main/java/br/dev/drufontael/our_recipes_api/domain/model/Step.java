package br.dev.drufontael.our_recipes_api.domain.model;

public class Step {

    private Long id;
    private int stepNumber;
    private String description;
    private Recipe recipe;

    public Step() {
    }

    public Step(Long id, int stepNumber, String description) {
        this.id = id;
        this.stepNumber = stepNumber;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
