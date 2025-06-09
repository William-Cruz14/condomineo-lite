package br.com.condomineolite.services;

import br.com.condomineolite.dtos.visitor.VisitorRequestDTO;
import br.com.condomineolite.dtos.visitor.VisitorResponseDTO;
import br.com.condomineolite.models.Visitor;
import br.com.condomineolite.repositories.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Transactional(readOnly = true)
    public Page<VisitorResponseDTO> findAll(Pageable pageable) {
        return visitorRepository.findAll(pageable).map(VisitorResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<VisitorResponseDTO> findById(Long id) {
        return visitorRepository.findById(id).map(VisitorResponseDTO::new);
    }

    @Transactional
    public VisitorResponseDTO create(VisitorRequestDTO dto) {
        Visitor visitor = new Visitor();
        visitor.setName(dto.getName());
        visitor.setEmail(dto.getEmail());
        visitor.setPhone(dto.getPhone());
        visitor.setDocument(dto.getDocument());
        visitor = visitorRepository.save(visitor);
        return new VisitorResponseDTO(visitor);
    }

    @Transactional
    public Optional<VisitorResponseDTO> update(Long id, VisitorRequestDTO dto) {
        Optional<Visitor> visitorOpt = visitorRepository.findById(id);
        if (visitorOpt.isEmpty()) {
            return Optional.empty();
        }
        Visitor visitor = visitorOpt.get();
        visitor.setName(dto.getName());
        visitor.setEmail(dto.getEmail());
        visitor.setPhone(dto.getPhone());
        Visitor updated = visitorRepository.save(visitor);
        return Optional.of(new VisitorResponseDTO(updated));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!visitorRepository.existsById(id)) {
            return false;
        }
        visitorRepository.deleteById(id);
        return true;
    }
}
