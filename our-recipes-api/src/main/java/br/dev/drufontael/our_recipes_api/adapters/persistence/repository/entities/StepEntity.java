package br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities;

import br.dev.drufontael.our_recipes_api.domain.model.Recipe;
import br.dev.drufontael.our_recipes_api.domain.model.Step;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_step")
@Data
@NoArgsConstructor
public class StepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stepNumber;
    private String description;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private RecipeEntity recipe;

    public StepEntity(Step step, Recipe recipe) {
        id = step.getId();
        stepNumber = step.getStepNumber();
        description = step.getDescription();
    }

    public Step toDomain() {
        Step step = new Step();
        step.setId(id);
        step.setStepNumber(stepNumber);
        step.setDescription(description);
        return step;
    }
}
