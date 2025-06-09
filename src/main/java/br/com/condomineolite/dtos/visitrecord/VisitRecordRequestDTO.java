package br.com.condomineolite.dtos.visitrecord;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitRecordRequestDTO {
    private Long visitorId;
    private Long residentId;
    private Long apartmentId;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private String observation;
}
