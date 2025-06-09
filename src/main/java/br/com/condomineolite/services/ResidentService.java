package br.com.condomineolite.services;

import br.com.condomineolite.dtos.resident.ResidentRequestDTO;
import br.com.condomineolite.dtos.resident.ResidentResponseDTO;
import br.com.condomineolite.models.Resident;
import br.com.condomineolite.repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Transactional(readOnly = true)
    public Page<ResidentResponseDTO> findAll(Pageable pageable) {
        return residentRepository.findAll(pageable).map(ResidentResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<ResidentResponseDTO> findById(Long id) {
        return residentRepository.findById(id).map(ResidentResponseDTO::new);
    }

    @Transactional
    public ResidentResponseDTO create(ResidentRequestDTO dto) {
        Resident resident = new Resident();
        resident.setName(dto.getName());
        resident.setEmail(dto.getEmail());
        resident.setPhone(dto.getPhone());
        resident.setDocument(dto.getDocument());
        resident = residentRepository.save(resident);
        return new ResidentResponseDTO(resident);
    }

    @Transactional
    public Optional<ResidentResponseDTO> update(Long id, ResidentRequestDTO dto) {
        Optional<Resident> residentOpt = residentRepository.findById(id);
        if (residentOpt.isEmpty()) {
            return Optional.empty();
        }
        Resident resident = residentOpt.get();
        resident.setName(dto.getName());
        resident.setEmail(dto.getEmail());
        resident.setPhone(dto.getPhone());
        resident.setDocument(dto.getDocument());
        Resident updated = residentRepository.save(resident);
        return Optional.of(new ResidentResponseDTO(updated));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!residentRepository.existsById(id)) {
            return false;
        }
        residentRepository.deleteById(id);
        return true;
    }
}
