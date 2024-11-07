package br.dev.drufontael.our_recipes_api.domain.ports.out;

import br.dev.drufontael.our_recipes_api.domain.model.Review;

import java.util.List;
import java.util.Optional;

public interface PersistenceReviewPort {
    Review save(Review review);
    void delete(Review review);
    Review update(Review review);
    Optional<Review> findById(Long id);
    List<Review> findAll();
}
