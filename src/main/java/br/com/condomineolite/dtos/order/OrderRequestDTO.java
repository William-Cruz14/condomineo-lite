package br.com.condomineolite.dtos.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
    private String code;
    private String description;
    private Boolean received = false;
    private Boolean delivered = false;
    private Long residentId;
}
