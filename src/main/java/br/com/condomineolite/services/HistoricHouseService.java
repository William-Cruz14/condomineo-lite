package br.com.condomineolite.services;

import br.com.condomineolite.dtos.historichouse.HistoricHouseRequestDTO;
import br.com.condomineolite.dtos.historichouse.HistoricHouseResponseDTO;
import br.com.condomineolite.models.Apartment;
import br.com.condomineolite.models.HistoricHouse;
import br.com.condomineolite.models.Resident;
import br.com.condomineolite.repositories.ApartmentRepository;
import br.com.condomineolite.repositories.HistoricHouseRepository;
import br.com.condomineolite.repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HistoricHouseService {

    @Autowired
    private HistoricHouseRepository historicHouseRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Transactional(readOnly = true)
    public Page<HistoricHouseResponseDTO> findAll(Pageable pageable) {
        return historicHouseRepository.findAll(pageable).map(HistoricHouseResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<HistoricHouseResponseDTO> findById(Long id) {
        return historicHouseRepository.findById(id).map(HistoricHouseResponseDTO::new);
    }

    @Transactional
    public HistoricHouseResponseDTO create(HistoricHouseRequestDTO dto) {
        Resident resident = residentRepository.findById(dto.getResidentId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        Apartment apartment = apartmentRepository.findById(dto.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartamento não encontrado"));

        HistoricHouse historicHouse = new HistoricHouse();
        historicHouse.setResident(resident);
        historicHouse.setApartment(apartment);
        historicHouse.setStartDate(dto.getStartDate());
        historicHouse.setEndDate(dto.getEndDate());

        historicHouse = historicHouseRepository.save(historicHouse);
        return new HistoricHouseResponseDTO(historicHouse);
    }

    @Transactional
    public Optional<HistoricHouseResponseDTO> update(Long id, HistoricHouseRequestDTO dto) {
        Optional<HistoricHouse> historicHouseOpt = historicHouseRepository.findById(id);
        if (historicHouseOpt.isEmpty()) {
            return Optional.empty();
        }
        HistoricHouse historicHouse = historicHouseOpt.get();
        Resident resident = residentRepository.findById(dto.getResidentId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        Apartment apartment = apartmentRepository.findById(dto.getApartmentId())
                .orElseThrow(() -> new RuntimeException("Apartamento não encontrado"));

        historicHouse.setResident(resident);
        historicHouse.setApartment(apartment);
        historicHouse.setStartDate(dto.getStartDate());
        historicHouse.setEndDate(dto.getEndDate());

        HistoricHouse updated = historicHouseRepository.save(historicHouse);
        return Optional.of(new HistoricHouseResponseDTO(updated));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!historicHouseRepository.existsById(id)) {
            return false;
        }
        historicHouseRepository.deleteById(id);
        return true;
    }
}