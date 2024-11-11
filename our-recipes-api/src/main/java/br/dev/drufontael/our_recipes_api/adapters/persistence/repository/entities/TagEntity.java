package br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities;


import br.dev.drufontael.our_recipes_api.domain.model.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity {

    @Id
    @Column(name = "tag_id", unique = true, nullable = false, length = 50)
    private String name;
    private String description;

    public TagEntity(Tag tag) {
        this.name = tag.getName();
        this.description = tag.getDescription();
    }

    public Tag toDomain() {
        Tag tag = new Tag(name);
        tag.setDescription(description);
        return tag;
    }
}
