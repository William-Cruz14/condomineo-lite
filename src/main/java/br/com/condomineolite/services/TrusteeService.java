package br.com.condomineolite.services;

import br.com.condomineolite.dtos.trustee.TrusteeRequestDTO;
import br.com.condomineolite.dtos.trustee.TrusteeResponseDTO;
import br.com.condomineolite.models.Trustee;
import br.com.condomineolite.repositories.TrusteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TrusteeService {

    @Autowired
    private TrusteeRepository trusteeRepository;

    @Transactional(readOnly = true)
    public Page<TrusteeResponseDTO> findAll(Pageable pageable) {
        return trusteeRepository.findAll(pageable).map(TrusteeResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<TrusteeResponseDTO> findById(Long id) {
        return trusteeRepository.findById(id).map(TrusteeResponseDTO::new);
    }

    @Transactional
    public TrusteeResponseDTO create(TrusteeRequestDTO dto) {
        Trustee trustee = new Trustee();
        trustee.setName(dto.getName());
        trustee.setEmail(dto.getEmail());
        trustee.setPhone(dto.getPhone());
        trustee.setDocument(dto.getDocument());
        trustee = trusteeRepository.save(trustee);
        return new TrusteeResponseDTO(trustee);
    }

    @Transactional
    public Optional<TrusteeResponseDTO> update(Long id, TrusteeRequestDTO dto) {
        Optional<Trustee> trusteeOpt = trusteeRepository.findById(id);
        if (trusteeOpt.isEmpty()) {
            return Optional.empty();
        }
        Trustee trustee = trusteeOpt.get();
        trustee.setName(dto.getName());
        trustee.setEmail(dto.getEmail());
        trustee.setPhone(dto.getPhone());
        trustee.setDocument(dto.getDocument());
        Trustee updated = trusteeRepository.save(trustee);
        return Optional.of(new TrusteeResponseDTO(updated));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!trusteeRepository.existsById(id)) {
            return false;
        }
        trusteeRepository.deleteById(id);
        return true;
    }
}