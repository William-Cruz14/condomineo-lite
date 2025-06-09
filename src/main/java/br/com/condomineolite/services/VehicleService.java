package br.com.condomineolite.services;

import br.com.condomineolite.dtos.vehicle.VehicleRequestDTO;
import br.com.condomineolite.dtos.vehicle.VehicleResponseDTO;
import br.com.condomineolite.models.Person;
import br.com.condomineolite.models.Vehicle;
import br.com.condomineolite.repositories.PersonRepository;
import br.com.condomineolite.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VehicleService {


    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Page<VehicleResponseDTO> findAll(final Pageable pageable) {
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);
        return vehiclePage.map(VehicleResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<VehicleResponseDTO> findById(Long id) {
        return vehicleRepository.findById(id).map(VehicleResponseDTO::new);
    }

    @Transactional
    public VehicleResponseDTO create(VehicleRequestDTO dto) {
        Person owner = personRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));
        Vehicle vehicle = new Vehicle();
        vehicle.setPlate(dto.getPlate());
        vehicle.setModel(dto.getModel());
        vehicle.setColor(dto.getColor());
        vehicle.setOwner(owner);
        vehicle = vehicleRepository.save(vehicle);
        return new VehicleResponseDTO(vehicle);
    }

    @Transactional
    public Optional<VehicleResponseDTO> update(Long id, VehicleRequestDTO dto) {
        return vehicleRepository.findById(id).map(vehicle -> {
            Person owner = personRepository.findById(dto.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));
            vehicle.setPlate(dto.getPlate());
            vehicle.setModel(dto.getModel());
            vehicle.setColor(dto.getColor());
            vehicle.setOwner(owner);
            vehicle = vehicleRepository.save(vehicle);
            return new VehicleResponseDTO(vehicle);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!vehicleRepository.existsById(id)) return false;
        vehicleRepository.deleteById(id);
        return true;
    }
}
