package br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities;

import br.dev.drufontael.our_recipes_api.domain.model.Recipe;
import br.dev.drufontael.our_recipes_api.domain.model.Tag;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_recipe_tag")
@Data
@NoArgsConstructor
public class RecipeTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private RecipeEntity recipe;
    @JoinColumn(name = "tag_id")
    private TagEntity tag;

    public RecipeTagEntity(Tag tag, Recipe recipe) {
        this.tag = new TagEntity(tag);
        this.recipe = new RecipeEntity(recipe);
    }

    public Tag toDomain() {
        return this.tag.toDomain();
    }
}
