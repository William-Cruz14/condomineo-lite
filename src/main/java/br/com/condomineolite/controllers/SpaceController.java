package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.space.SpaceRequestDTO;
import br.com.condomineolite.dtos.space.SpaceResponseDTO;
import br.com.condomineolite.services.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/spaces")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @GetMapping
    public ResponseEntity<Page<SpaceResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(spaceService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceResponseDTO> findById(@PathVariable Long id) {
        return spaceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SpaceResponseDTO> create(@RequestBody SpaceRequestDTO dto) {
        SpaceResponseDTO response = spaceService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceResponseDTO> update(@PathVariable Long id, @RequestBody SpaceRequestDTO dto) {
        return spaceService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (spaceService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
