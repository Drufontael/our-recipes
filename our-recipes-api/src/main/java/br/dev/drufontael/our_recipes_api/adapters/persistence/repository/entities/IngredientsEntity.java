package br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities;

import br.dev.drufontael.our_recipes_api.domain.model.Ingredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false,length = 50)
    private String name;
    private String description;

    public IngredientsEntity(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.description = ingredient.getDescription();
    }

    public Ingredient toDomain() {
        return new Ingredient(id, name, description);
    }
}
