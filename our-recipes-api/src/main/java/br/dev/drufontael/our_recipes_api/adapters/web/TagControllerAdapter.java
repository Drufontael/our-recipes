package br.dev.drufontael.our_recipes_api.adapters.web;


import br.dev.drufontael.our_recipes_api.adapters.web.dto.TagDto;
import br.dev.drufontael.our_recipes_api.domain.model.Tag;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageTagPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/tags")
public class TagControllerAdapter {

    private final ManageTagPort managePort;

    @PostMapping
    public ResponseEntity<TagDto> register(@RequestBody TagDto request) {
        Tag tag = managePort.register(request.toDomain());
        return ResponseEntity.ok(new TagDto(tag.getName(), tag.getDescription()));
    }

    @PostMapping("/multiple")
    public ResponseEntity<Void> register(@RequestBody String[] tagsNames) {
       managePort.register(tagsNames);
       return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> findAll() {
        return ResponseEntity.ok(managePort.findAll().stream()
                .map(tag -> new TagDto(tag.getName(), tag.getDescription())).toList());
    }

    @GetMapping("{name}")
    public ResponseEntity<TagDto> findByName(@PathVariable String name) {
        Tag tag = managePort.findByName(name);
        return ResponseEntity.ok(new TagDto(tag.getName(), tag.getDescription()));
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        Tag tag =managePort.findByName(name);
        managePort.delete(tag);
        return ResponseEntity.noContent().build();
    }
}
