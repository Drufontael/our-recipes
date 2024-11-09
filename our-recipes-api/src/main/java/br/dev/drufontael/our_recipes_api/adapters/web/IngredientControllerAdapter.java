package br.dev.drufontael.our_recipes_api.adapters.web;

import br.dev.drufontael.our_recipes_api.adapters.web.dto.IngredientRequest;
import br.dev.drufontael.our_recipes_api.adapters.web.dto.IngredientResponse;
import br.dev.drufontael.our_recipes_api.domain.model.Ingredient;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageIngredientPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/ingredients")
@AllArgsConstructor
public class IngredientControllerAdapter {

    private final ManageIngredientPort manageIngredientPort;

    @PostMapping
    public ResponseEntity<IngredientResponse> register(@RequestBody IngredientRequest request){
        Ingredient ingredient = manageIngredientPort.register(request.toDomain());
        return ResponseEntity.ok(new IngredientResponse(ingredient.getId(),ingredient.getName(),ingredient.getDescription()));
    }

    @PostMapping("/all")
    public ResponseEntity<Void> register(@RequestBody List<String> names){
        manageIngredientPort.register(names.toArray(new String[0]));
        return ResponseEntity.ok().build();
    }
}
