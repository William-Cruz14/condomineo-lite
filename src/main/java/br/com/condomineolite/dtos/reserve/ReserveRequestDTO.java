package br.com.condomineolite.dtos.reserve;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReserveRequestDTO {
    private Long residentId;
    private Long spaceId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String status;
}
