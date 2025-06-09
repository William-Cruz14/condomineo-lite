package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.trustee.TrusteeRequestDTO;
import br.com.condomineolite.dtos.trustee.TrusteeResponseDTO;
import br.com.condomineolite.services.TrusteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/trustees")
public class TrusteeController {

    @Autowired
    private TrusteeService trusteeService;

    @GetMapping
    public ResponseEntity<Page<TrusteeResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(trusteeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrusteeResponseDTO> findById(@PathVariable Long id) {
        return trusteeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TrusteeResponseDTO> create(@RequestBody TrusteeRequestDTO dto) {
        TrusteeResponseDTO response = trusteeService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrusteeResponseDTO> update(@PathVariable Long id, @RequestBody TrusteeRequestDTO dto) {
        return trusteeService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (trusteeService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
