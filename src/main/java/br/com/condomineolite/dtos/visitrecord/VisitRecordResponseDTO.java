package br.com.condomineolite.dtos.visitrecord;

import br.com.condomineolite.models.VisitRecord;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitRecordResponseDTO {
    private Long id;
    private Long visitorId;
    private Long residentId;
    private Long apartmentId;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private String observation;

    public VisitRecordResponseDTO(VisitRecord entity) {
        this.id = entity.getId();
        this.visitorId = entity.getVisitor().getId();
        this.residentId = entity.getResident().getId();
        this.apartmentId = entity.getApartment().getId();
        this.entryDate = entity.getEntryDate();
        this.exitDate = entity.getExitDate();
        this.observation = entity.getObservation();
    }
}