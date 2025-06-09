package br.com.condomineolite.dtos.historichouse;

import br.com.condomineolite.models.HistoricHouse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class HistoricHouseResponseDTO {
    private Long id;
    private Long residentId;
    private Long apartmentId;
    private LocalDate startDate;
    private LocalDate endDate;

    public HistoricHouseResponseDTO(HistoricHouse entity) {
        this.id = entity.getId();
        this.residentId = entity.getResident().getId();
        this.apartmentId = entity.getApartment().getId();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
    }
}
