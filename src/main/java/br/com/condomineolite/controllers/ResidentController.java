package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.resident.ResidentRequestDTO;
import br.com.condomineolite.dtos.resident.ResidentResponseDTO;
import br.com.condomineolite.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @GetMapping
    public ResponseEntity<Page<ResidentResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(residentService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidentResponseDTO> findById(@PathVariable Long id) {
        return residentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResidentResponseDTO> create(@RequestBody ResidentRequestDTO dto) {
        ResidentResponseDTO response = residentService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResidentResponseDTO> update(@PathVariable Long id, @RequestBody ResidentRequestDTO dto) {
        return residentService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (residentService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}