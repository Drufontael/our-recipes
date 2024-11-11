package br.dev.drufontael.our_recipes_api.domain.model;

import java.util.HashSet;
import java.util.Set;

public class Tag {

    private String name;
    private String description;


    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
