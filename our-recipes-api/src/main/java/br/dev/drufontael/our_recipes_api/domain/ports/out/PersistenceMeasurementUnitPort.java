package br.dev.drufontael.our_recipes_api.domain.ports.out;

import br.dev.drufontael.our_recipes_api.domain.model.MeasurementUnit;

import java.util.List;
import java.util.Optional;

public interface PersistenceMeasurementUnitPort {
    MeasurementUnit save(MeasurementUnit measurementUnit);
    void delete(MeasurementUnit measurementUnit);
    Optional<MeasurementUnit> findById(Long id);
    List<MeasurementUnit> findAll();
}
