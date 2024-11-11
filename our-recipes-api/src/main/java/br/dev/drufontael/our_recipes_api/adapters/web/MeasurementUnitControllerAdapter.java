package br.dev.drufontael.our_recipes_api.adapters.web;

import br.dev.drufontael.our_recipes_api.adapters.web.dto.MeasurementUnitDto;
import br.dev.drufontael.our_recipes_api.domain.model.MeasurementUnit;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageMeasurementUnitPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/measurement-units")
@AllArgsConstructor
public class MeasurementUnitControllerAdapter {

    private final ManageMeasurementUnitPort managePort;

    @PostMapping
    public ResponseEntity<MeasurementUnitDto> register(@RequestBody MeasurementUnitDto request){
        MeasurementUnit measurementUnit = managePort.register(request.toDomain());
        return ResponseEntity.ok(new MeasurementUnitDto(measurementUnit.getId(),
                measurementUnit.getName(),
                measurementUnit.getAbbreviation()));
    }

    @GetMapping
    public ResponseEntity<List<MeasurementUnitDto>> getAll(){
        List<MeasurementUnit> measurementUnits = managePort.findAll();
        return ResponseEntity.ok(measurementUnits.stream().map(measurementUnit -> new MeasurementUnitDto(measurementUnit.getId(),
                measurementUnit.getName(),
                measurementUnit.getAbbreviation())).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<MeasurementUnitDto> findById(@PathVariable("id") Long id){
        MeasurementUnit measurementUnit = managePort.findById(id);
        return ResponseEntity.ok(new MeasurementUnitDto(measurementUnit.getId(),
                measurementUnit.getName(),
                measurementUnit.getAbbreviation()));
    }

    @PutMapping("{id}")
    public ResponseEntity<MeasurementUnitDto> update(@PathVariable("id") Long id, @RequestBody MeasurementUnitDto request){
        MeasurementUnit measurementUnit = managePort.findById(id);
        measurementUnit.setName(request.name());
        measurementUnit.setAbbreviation(request.abbreviation());
        return ResponseEntity.ok(new MeasurementUnitDto(measurementUnit.getId(),
                measurementUnit.getName(),
                measurementUnit.getAbbreviation()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        MeasurementUnit measurementUnit = managePort.findById(id);
        managePort.delete(measurementUnit);
        return ResponseEntity.noContent().build();
    }


}
