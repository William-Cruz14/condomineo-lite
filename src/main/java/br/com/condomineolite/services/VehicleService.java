package br.com.condomineolite.services;

import br.com.condomineolite.dtos.vehicle.VehicleResponseDTO;
import br.com.condomineolite.models.Vehicle;
import br.com.condomineolite.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public Page<VehicleResponseDTO> findAll(final Pageable pageable) {
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);

        return vehiclePage.map(VehicleResponseDTO::new);
    }
}
