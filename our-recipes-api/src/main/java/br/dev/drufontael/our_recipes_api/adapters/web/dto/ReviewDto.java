package br.dev.drufontael.our_recipes_api.adapters.web.dto;

import br.dev.drufontael.our_recipes_api.domain.model.Review;

import java.time.LocalDate;

public record ReviewDto(Long id,
                        int rating,
                        String comment,
                        LocalDate date) {
    public Review toDomain() {
        return new Review(id, rating, comment, date);
    }
}
