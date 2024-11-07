package br.dev.drufontael.our_recipes_api.domain.model;

public class Step implements Comparable<Step> {


    private int stepNumber;
    private String description;
    private Recipe recipe;//TODO: Verificar se é necessário   >

    public Step() {
    }

    public Step( int stepNumber, String description) {

        this.stepNumber = stepNumber;
        this.description = description;
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

    @Override
    public int compareTo(Step o) {
        if (this.getStepNumber() > o.getStepNumber()) return 1;
        if (this.getStepNumber() < o.getStepNumber()) return -1;
        return 0;
    }
}
