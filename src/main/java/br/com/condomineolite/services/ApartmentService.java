package br.com.condomineolite.services;

import br.com.condomineolite.dtos.apartment.ApartmentResponseDTO;
import br.com.condomineolite.models.Apartment;
import br.com.condomineolite.repositories.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Transactional(readOnly = true)
    public Page<ApartmentResponseDTO> findAll(Pageable pageable) {
        Page<Apartment> apartmentPage = apartmentRepository.findAll(pageable);
        return apartmentPage.map(ApartmentResponseDTO::new);
    }

}
