package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.visitor.VisitorRequestDTO;
import br.com.condomineolite.dtos.visitor.VisitorResponseDTO;
import br.com.condomineolite.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping
    public ResponseEntity<Page<VisitorResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(visitorService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitorResponseDTO> findById(@PathVariable Long id) {
        return visitorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VisitorResponseDTO> create(@RequestBody VisitorRequestDTO dto) {
        VisitorResponseDTO response = visitorService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitorResponseDTO> update(@PathVariable Long id, @RequestBody VisitorRequestDTO dto) {
        return visitorService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (visitorService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
