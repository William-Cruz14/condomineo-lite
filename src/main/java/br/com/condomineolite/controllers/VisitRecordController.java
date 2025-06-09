package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.visitrecord.VisitRecordRequestDTO;
import br.com.condomineolite.dtos.visitrecord.VisitRecordResponseDTO;
import br.com.condomineolite.services.VisitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/visit-records")
public class VisitRecordController {

    @Autowired
    private VisitRecordService visitRecordService;

    @GetMapping
    public ResponseEntity<Page<VisitRecordResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(visitRecordService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitRecordResponseDTO> findById(@PathVariable Long id) {
        return visitRecordService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VisitRecordResponseDTO> create(@RequestBody VisitRecordRequestDTO dto) {
        return ResponseEntity.ok(visitRecordService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitRecordResponseDTO> update(@PathVariable Long id, @RequestBody VisitRecordRequestDTO dto) {
        return visitRecordService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (visitRecordService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}