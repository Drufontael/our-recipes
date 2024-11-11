package br.dev.drufontael.our_recipes_api.adapters.persistence;

import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.MeasurementUnitRepository;
import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities.MeasurementUnitEntity;
import br.dev.drufontael.our_recipes_api.domain.model.MeasurementUnit;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceMeasurementUnitPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MeasurementUnitPersistenceAdapter implements PersistenceMeasurementUnitPort {

    private final MeasurementUnitRepository repository;

    @Override
    public MeasurementUnit save(MeasurementUnit measurementUnit) {
        return repository.save(new MeasurementUnitEntity(measurementUnit)).toDomain();
    }

    @Override
    public void delete(MeasurementUnit measurementUnit) {

    }

    @Override
    public Optional<MeasurementUnit> findById(Long id) {
        Optional<MeasurementUnitEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            return Optional.of(entity.get().toDomain());
        }
        return Optional.empty();
    }

    @Override
    public List<MeasurementUnit> findAll() {
        return repository.findAll().stream().map(MeasurementUnitEntity::toDomain).toList();
    }
}
