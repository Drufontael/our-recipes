package br.dev.drufontael.our_recipes_api.domain.model;

public class MeasurementUnit {

    private Long id;
    private String name;
    private String abbreviation;

    public MeasurementUnit() {
    }

    public MeasurementUnit(Long id, String name, String abbreviation) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
