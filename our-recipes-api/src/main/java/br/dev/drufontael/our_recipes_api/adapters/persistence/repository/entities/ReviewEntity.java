package br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities;

import br.dev.drufontael.our_recipes_api.domain.model.Review;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name="tb_review")
@Data
@NoArgsConstructor
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    private String comment;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private RecipeEntity recipe;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public ReviewEntity(Review review,RecipeEntity recipe) {

        this.id = review.getId();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.date = review.getDate();
        this.recipe = recipe;
        this.user = recipe.getAuthor();
    }

    public Review toDomain() {
        Review review = new Review(this.id, this.rating, this.comment, this.date);
     //   review.setRecipe(this.recipe.toDomain());
        if(this.user != null) review.setUser(this.user.toDomain());
        return review;
    }
}
