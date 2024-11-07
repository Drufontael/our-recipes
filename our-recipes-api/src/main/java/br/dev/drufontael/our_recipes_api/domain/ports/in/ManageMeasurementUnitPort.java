package br.dev.drufontael.our_recipes_api.domain.ports.in;

import br.dev.drufontael.our_recipes_api.domain.model.MeasurementUnit;

import java.util.List;

public interface ManageMeasurementUnitPort {
    MeasurementUnit register(MeasurementUnit measurementUnit);
    void update(MeasurementUnit measurementUnit);
    void delete(MeasurementUnit measurementUnit);
    MeasurementUnit findById(Long id);
    List<MeasurementUnit> findAll();
}
