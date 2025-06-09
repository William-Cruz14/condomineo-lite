package br.com.condomineolite.services;

import br.com.condomineolite.dtos.visitrecord.VisitRecordRequestDTO;
import br.com.condomineolite.dtos.visitrecord.VisitRecordResponseDTO;
import br.com.condomineolite.models.*;
import br.com.condomineolite.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VisitRecordService {

    @Autowired
    private VisitRecordRepository visitRecordRepository;
    @Autowired
    private VisitorRepository visitorRepository;
    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Transactional(readOnly = true)
    public Page<VisitRecordResponseDTO> findAll(Pageable pageable) {
        return visitRecordRepository.findAll(pageable).map(VisitRecordResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<VisitRecordResponseDTO> findById(Long id) {
        return visitRecordRepository.findById(id).map(VisitRecordResponseDTO::new);
    }

    @Transactional
    public VisitRecordResponseDTO create(VisitRecordRequestDTO dto) {
        Visitor visitor = visitorRepository.findById(dto.getVisitorId())
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));
        Resident resident = residentRepository.findById(dto.getResidentId())
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
        Apartment apartment = apartmentRepository.findById(dto.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartamento não encontrado"));

        VisitRecord entity = new VisitRecord();
        entity.setVisitor(visitor);
        entity.setResident(resident);
        entity.setApartment(apartment);
        entity.setEntryDate(dto.getEntryDate());
        entity.setExitDate(dto.getExitDate());
        entity.setObservation(dto.getObservation());

        entity = visitRecordRepository.save(entity);
        return new VisitRecordResponseDTO(entity);
    }

    @Transactional
    public Optional<VisitRecordResponseDTO> update(Long id, VisitRecordRequestDTO dto) {
        return visitRecordRepository.findById(id).map(entity -> {
            Visitor visitor = visitorRepository.findById(dto.getVisitorId())
                    .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));
            Resident resident = residentRepository.findById(dto.getResidentId())
                    .orElseThrow(() -> new RuntimeException("Morador não encontrado"));
            Apartment apartment = apartmentRepository.findById(dto.getApartmentId())
                    .orElseThrow(() -> new RuntimeException("Apartamento não encontrado"));

            entity.setVisitor(visitor);
            entity.setResident(resident);
            entity.setApartment(apartment);
            entity.setEntryDate(dto.getEntryDate());
            entity.setExitDate(dto.getExitDate());
            entity.setObservation(dto.getObservation());

            entity = visitRecordRepository.save(entity);
            return new VisitRecordResponseDTO(entity);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!visitRecordRepository.existsById(id)) return false;
        visitRecordRepository.deleteById(id);
        return true;
    }
}