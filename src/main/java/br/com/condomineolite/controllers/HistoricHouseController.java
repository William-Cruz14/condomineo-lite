package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.historichouse.HistoricHouseRequestDTO;
import br.com.condomineolite.dtos.historichouse.HistoricHouseResponseDTO;
import br.com.condomineolite.services.HistoricHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/historic-houses")
public class HistoricHouseController {

    @Autowired
    private HistoricHouseService historicHouseService;

    @GetMapping
    public ResponseEntity<Page<HistoricHouseResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(historicHouseService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricHouseResponseDTO> findById(@PathVariable Long id) {
        return historicHouseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HistoricHouseResponseDTO> create(@RequestBody HistoricHouseRequestDTO dto) {
        HistoricHouseResponseDTO response = historicHouseService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoricHouseResponseDTO> update(@PathVariable Long id, @RequestBody HistoricHouseRequestDTO dto) {
        return historicHouseService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (historicHouseService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}