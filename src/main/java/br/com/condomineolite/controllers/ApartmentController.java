package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.apartment.ApartmentResponseDTO;
import br.com.condomineolite.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping
    public ResponseEntity<Page<ApartmentResponseDTO>> findAll(Pageable pageable) {
        Page<ApartmentResponseDTO> apartments = apartmentService.findAll(pageable);
        return ResponseEntity.ok(apartments);
    }
}
