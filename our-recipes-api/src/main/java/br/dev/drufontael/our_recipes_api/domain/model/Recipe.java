package br.dev.drufontael.our_recipes_api.domain.model;


import java.util.*;

public class Recipe implements Comparable<Recipe> {

    private Long id;
    private String name;
    private String description;
    private int servingSize;
    private int preparationTime;
    private Difficulty difficulty;
    private Set<Tag> tags = new HashSet<Tag>();
    private Set<RecipeIngredient> ingredients = new HashSet<RecipeIngredient>();
    private List<Step> steps = new ArrayList<Step>();
    private List<Review> reviews = new ArrayList<Review>();
    private User author;

    public Recipe() {
    }

    public Recipe(Long id, String name, String description, int servingSize, int preparationTime, Difficulty difficulty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.servingSize = servingSize;
        this.preparationTime = preparationTime;
        this.difficulty = difficulty;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }



    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        Collections.sort(steps);
        return steps;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void addStep(Step step) {
        this.steps.add(step);
        step.setStepNumber(this.steps.size());
    }

    public void addIngredient(RecipeIngredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public double getRating() {
        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public int compareTo(Recipe o) {
        if (this.getRating() > o.getRating()) return -1;
        if (this.getRating() < o.getRating()) return 1;
        return 0;
    }
}
