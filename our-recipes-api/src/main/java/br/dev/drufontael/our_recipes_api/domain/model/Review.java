package br.dev.drufontael.our_recipes_api.domain.model;

import br.dev.drufontael.our_recipes_api.domain.exception.RattingOutOfRangeException;

import java.time.LocalDate;

public class Review {
    private Long id;
    private int rating;
    private String comment;
    private LocalDate date;
  //  private Recipe recipe;
    private User user;

    public Review() {
    }

    public Review(Long id, int rating, String comment, LocalDate date) {
        this.id = id;
        if(rating < 1 || rating > 5) throw new RattingOutOfRangeException();
        this.rating = rating;
        this.comment = comment;
        this.date = date==null?LocalDate.now():date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if(rating < 1 || rating > 5) throw new RattingOutOfRangeException();
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

/*    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
