package br.com.condomineolite.dtos.order;

import br.com.condomineolite.models.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDTO {

    private Long id;
    private String code;
    private String description;
    private Boolean received;
    private Boolean delivered;

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.code = order.getCode();
        this.description = order.getDescription();
        this.received = order.getReceived();
        this.delivered = order.getDelivered();
    }
}
