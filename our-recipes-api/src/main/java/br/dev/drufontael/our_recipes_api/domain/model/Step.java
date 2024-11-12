package br.dev.drufontael.our_recipes_api.domain.model;

public class Step implements Comparable<Step> {


    private Long id;
    private int stepNumber;
    private String description;


    public Step() {
    }

    public Step( int stepNumber, String description) {

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



    @Override
    public int compareTo(Step o) {
        if (this.getStepNumber() > o.getStepNumber()) return 1;
        if (this.getStepNumber() < o.getStepNumber()) return -1;
        return 0;
    }
}
