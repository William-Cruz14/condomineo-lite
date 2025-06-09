package br.com.condomineolite.dtos.reserve;

import br.com.condomineolite.models.Reserve;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ReserveResponseDTO {
    private Long id;
    private Long residentId;
    private Long spaceId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String status;


    public ReserveResponseDTO(Reserve reserve) {
        this.id = reserve.getId();
        this.residentId = reserve.getResident().getId();
        this.spaceId = reserve.getSpace().getId();
        this.startDateTime = reserve.getStartDateTime();
        this.endDateTime = reserve.getEndDateTime();
        this.status = reserve.getStatus();
    }
}
