package br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities;

import br.dev.drufontael.our_recipes_api.domain.model.MeasurementUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_measurement_unit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementUnitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    @Column(unique = true, nullable = false, length = 10)
    private String abbreviation;

    public MeasurementUnitEntity(MeasurementUnit measurementUnit) {
        this.id = measurementUnit.getId();
        this.name = measurementUnit.getName();
        this.abbreviation = measurementUnit.getAbbreviation();
    }

    public MeasurementUnit toDomain() {
        return new MeasurementUnit(this.id, this.name, this.abbreviation);
    }

}
