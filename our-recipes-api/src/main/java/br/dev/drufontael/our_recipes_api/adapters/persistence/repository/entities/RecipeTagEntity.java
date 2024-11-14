package br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities;

import br.dev.drufontael.our_recipes_api.domain.model.Tag;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_recipe_tag")
@Setter
@Getter
@NoArgsConstructor
public class RecipeTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private RecipeEntity recipe;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private TagEntity tag;

    public RecipeTagEntity(Tag tag, RecipeEntity recipe) {
        this.tag = new TagEntity(tag);
        this.recipe = recipe;
    }

    public Tag toDomain() {
        return this.tag.toDomain();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeTagEntity that = (RecipeTagEntity) o;
        return tag.equals(that.tag);
    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }
}
