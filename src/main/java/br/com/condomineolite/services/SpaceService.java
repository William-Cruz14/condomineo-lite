package br.com.condomineolite.services;

import br.com.condomineolite.dtos.space.SpaceRequestDTO;
import br.com.condomineolite.dtos.space.SpaceResponseDTO;
import br.com.condomineolite.models.Space;
import br.com.condomineolite.repositories.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Transactional(readOnly = true)
    public Page<SpaceResponseDTO> findAll(Pageable pageable) {
        return spaceRepository.findAll(pageable).map(SpaceResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<SpaceResponseDTO> findById(Long id) {
        return spaceRepository.findById(id).map(SpaceResponseDTO::new);
    }

    @Transactional
    public SpaceResponseDTO create(SpaceRequestDTO dto) {
        Space space = new Space();
        space.setName(dto.getName());
        space.setDescription(dto.getDescription());
        space.setCapacity(dto.getCapacity());
        space = spaceRepository.save(space);
        return new SpaceResponseDTO(space);
    }

    @Transactional
    public Optional<SpaceResponseDTO> update(Long id, SpaceRequestDTO dto) {
        Optional<Space> spaceOpt = spaceRepository.findById(id);
        if (spaceOpt.isEmpty()) {
            return Optional.empty();
        }
        Space space = spaceOpt.get();
        space.setName(dto.getName());
        space.setDescription(dto.getDescription());
        space.setCapacity(dto.getCapacity());
        Space updated = spaceRepository.save(space);
        return Optional.of(new SpaceResponseDTO(updated));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!spaceRepository.existsById(id)) {
            return false;
        }
        spaceRepository.deleteById(id);
        return true;
    }
}
