package br.dev.drufontael.our_recipes_api.domain.service;

import br.dev.drufontael.our_recipes_api.domain.exception.ResourceAlreadyExistsException;
import br.dev.drufontael.our_recipes_api.domain.exception.ResourceNotFoundException;
import br.dev.drufontael.our_recipes_api.domain.model.MeasurementUnit;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageMeasurementUnitPort;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceMeasurementUnitPort;

import java.util.List;

public class MeasurementUnitService implements ManageMeasurementUnitPort {

    private final PersistenceMeasurementUnitPort persistence;

    public MeasurementUnitService(PersistenceMeasurementUnitPort persistence) {
        this.persistence = persistence;
    }
    @Override
    public MeasurementUnit register(MeasurementUnit measurementUnit) {
        if(measurementUnit.getId()!=null) {
            persistence.findById(measurementUnit.getId()).ifPresent(measurementUnitPresent ->
            {
                throw new ResourceAlreadyExistsException("MeasurementUnit already exists");
            });
        }
        return persistence.save(measurementUnit);
    }

    @Override
    public void update(MeasurementUnit measurementUnit) {
        persistence.findById(measurementUnit.getId()).ifPresentOrElse(persistence::save,
                ()->{throw new ResourceNotFoundException("MeasurementUnit not found");});

    }

    @Override
    public void delete(MeasurementUnit measurementUnit) {
        persistence.findById(measurementUnit.getId()).ifPresentOrElse(persistence::delete,
                ()->{throw new ResourceNotFoundException("MeasurementUnit not found");});

    }

    @Override
    public MeasurementUnit findById(Long id) {
        return persistence.findById(id).orElseThrow(()->new ResourceNotFoundException("MeasurementUnit not found"));
    }

    @Override
    public List<MeasurementUnit> findAll() {
        return persistence.findAll();
    }
}
