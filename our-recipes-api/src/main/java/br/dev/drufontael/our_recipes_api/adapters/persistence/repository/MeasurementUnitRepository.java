package br.dev.drufontael.our_recipes_api.adapters.persistence.repository;

import br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities.MeasurementUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnitEntity, Long> {
}
