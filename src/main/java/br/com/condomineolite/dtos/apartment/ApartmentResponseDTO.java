package br.com.condomineolite.dtos.apartment;

import br.com.condomineolite.models.Apartment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartmentResponseDTO {
    private Long id;
    private Integer number;
    private String block;
    private Integer floor;

    public ApartmentResponseDTO(Apartment apartment) {
        this.id = apartment.getId();
        this.number = apartment.getNumber();
        this.block = apartment.getBlock();
        this.floor = apartment.getFloor();
    }

}
