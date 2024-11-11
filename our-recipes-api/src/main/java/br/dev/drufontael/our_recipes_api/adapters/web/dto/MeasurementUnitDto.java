package br.dev.drufontael.our_recipes_api.adapters.web.dto;

import br.dev.drufontael.our_recipes_api.domain.model.MeasurementUnit;

public record MeasurementUnitDto(Long id, String name, String abbreviation) {
    public MeasurementUnit toDomain() { return new MeasurementUnit(id, name, abbreviation); }
}
