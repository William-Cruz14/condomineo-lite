package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.reserve.ReserveRequestDTO;
import br.com.condomineolite.dtos.reserve.ReserveResponseDTO;
import br.com.condomineolite.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/reserves")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @GetMapping
    public ResponseEntity<Page<ReserveResponseDTO>> findAll(Pageable pageable) {
        Page<ReserveResponseDTO> reserves = reserveService.findAll(pageable);
        return ResponseEntity.ok(reserves);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReserveResponseDTO> findById(@PathVariable Long id) {
        return reserveService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReserveResponseDTO> create(@RequestBody ReserveRequestDTO dto) {
        ReserveResponseDTO response = reserveService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReserveResponseDTO> update(@PathVariable Long id, @RequestBody ReserveRequestDTO dto) {
        return reserveService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (reserveService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}