package br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities;

import br.dev.drufontael.our_recipes_api.domain.model.Step;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity(name = "tb_step")
@Setter
@Getter
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

    public StepEntity(Step step, RecipeEntity recipe) {
        id = step.getId();
        stepNumber = step.getStepNumber();
        description = step.getDescription();
        this.recipe = recipe;
    }

    public Step toDomain() {
        Step step = new Step();
        step.setId(id);
        step.setStepNumber(stepNumber);
        step.setDescription(description);
        return step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StepEntity that = (StepEntity) o;
        return stepNumber == that.stepNumber && Objects.equals(recipe, that.recipe);
    }

    @Override
    public int hashCode() {
        int result = stepNumber;
        result = 31 * result + Objects.hashCode(recipe);
        return result;
    }
}
