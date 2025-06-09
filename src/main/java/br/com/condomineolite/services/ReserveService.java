package br.com.condomineolite.services;

import br.com.condomineolite.dtos.reserve.ReserveRequestDTO;
import br.com.condomineolite.dtos.reserve.ReserveResponseDTO;
import br.com.condomineolite.repositories.ReserveRepository;
import br.com.condomineolite.repositories.SpaceRepository;
import br.com.condomineolite.models.Reserve;
import br.com.condomineolite.models.Resident;
import br.com.condomineolite.models.Space;
import br.com.condomineolite.repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private SpaceRepository spaceRepository;

    @Transactional(readOnly = true)
    public Page<ReserveResponseDTO> findAll(Pageable pageable) {
        return reserveRepository.findAll(pageable).map(ReserveResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<ReserveResponseDTO> findById(Long id) {
        return reserveRepository.findById(id).map(ReserveResponseDTO::new);
    }

    @Transactional
    public ReserveResponseDTO create(ReserveRequestDTO dto) {
        Resident resident = residentRepository.findById(dto.getResidentId())
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
        Space space = spaceRepository.findById(dto.getSpaceId())
                .orElseThrow(() -> new RuntimeException("Espaço não encontrado"));

        Reserve reserve = new Reserve();
        reserve.setResident(resident);
        reserve.setSpace(space);
        reserve.setStartDateTime(dto.getStartDateTime());
        reserve.setEndDateTime(dto.getEndDateTime());
        reserve.setStatus(dto.getStatus());

        reserve = reserveRepository.save(reserve);
        return new ReserveResponseDTO(reserve);
    }

    @Transactional
    public Optional<ReserveResponseDTO> update(Long id, ReserveRequestDTO dto) {
        Optional<Reserve> reserveOpt = reserveRepository.findById(id);
        if (reserveOpt.isEmpty()) {
            return Optional.empty();
        }
        Reserve reserve = reserveOpt.get();
        Resident resident = residentRepository.findById(dto.getResidentId())
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
        Space space = spaceRepository.findById(dto.getSpaceId())
                .orElseThrow(() -> new RuntimeException("Espaço não encontrado"));

        reserve.setResident(resident);
        reserve.setSpace(space);
        reserve.setStartDateTime(dto.getStartDateTime());
        reserve.setEndDateTime(dto.getEndDateTime());
        reserve.setStatus(dto.getStatus());

        Reserve updated = reserveRepository.save(reserve);
        return Optional.of(new ReserveResponseDTO(updated));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!reserveRepository.existsById(id)) {
            return false;
        }
        reserveRepository.deleteById(id);
        return true;
    }
}
