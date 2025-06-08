package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.vehicle.VehicleResponseDTO;
import br.com.condomineolite.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<Page<VehicleResponseDTO>> findAll(Pageable pageable) {
        Page<VehicleResponseDTO> vehicles = vehicleService.findAll(pageable);
        return ResponseEntity.ok(vehicles);
    }
}
