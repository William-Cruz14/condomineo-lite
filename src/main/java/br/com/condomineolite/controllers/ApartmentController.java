package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.apartment.ApartmentResponseDTO;
import br.com.condomineolite.models.Apartment;
import br.com.condomineolite.repositories.ApartmentRepository;
import br.com.condomineolite.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping
    public ResponseEntity<Page<ApartmentResponseDTO>> findAll(Pageable pageable) {
        Page<ApartmentResponseDTO> apartments = apartmentService.findAll(pageable);
        return ResponseEntity.ok(apartments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> findById(@PathVariable Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        return apartment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Apartment> create(@RequestBody Apartment apartment) {
        Apartment saved = apartmentRepository.save(apartment);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apartment> update(@PathVariable Long id, @RequestBody Apartment apartment) {
        if (!apartmentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        apartment.setId(id);
        Apartment updated = apartmentRepository.save(apartment);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!apartmentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        apartmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
