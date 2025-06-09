package br.com.condomineolite.dtos.historichouse;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HistoricHouseRequestDTO {
    private Long residentId;
    private Long apartmentId;
    private LocalDate startDate;
    private LocalDate endDate;
}
