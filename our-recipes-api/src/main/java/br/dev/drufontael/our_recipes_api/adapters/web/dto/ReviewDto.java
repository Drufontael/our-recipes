package br.dev.drufontael.our_recipes_api.adapters.web.dto;

import br.dev.drufontael.our_recipes_api.domain.model.Review;
import br.dev.drufontael.our_recipes_api.domain.model.User;

import java.time.LocalDate;

public record ReviewDto(Long id,
                        int rating,
                        String comment,
                        LocalDate date,
                        String author) {
    public Review toDomain() {
        Review review = new Review(id, rating, comment, date);
        review.setUser(new User(null, author, null, null));
        return review;
    }

    public static ReviewDto fromDomain(Review review) {
        return new ReviewDto(review.getId(), review.getRating(), review.getComment(), review.getDate(), review.getUser().getUsername());
    }
}
