package br.com.condomineolite.dtos.vehicle;

import br.com.condomineolite.models.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponseDTO {

    private Long id;
    private String plate;
    private String model;
    private String color;

    public VehicleResponseDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.plate = vehicle.getPlate();
        this.model = vehicle.getModel();
        this.color = vehicle.getColor();
    }
}
