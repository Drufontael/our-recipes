package br.dev.drufontael.our_recipes_api.domain.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private List<Recipe> recipes = new ArrayList<Recipe>();
    private List<Review> reviews = new ArrayList<Review>();
    private List<Role> roles = new ArrayList<Role>();

    public User() {
    }

    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
