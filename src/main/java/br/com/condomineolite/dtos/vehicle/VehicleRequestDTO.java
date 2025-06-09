package br.com.condomineolite.dtos.vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleRequestDTO {
    private String plate;
    private String model;
    private String color;
    private Long ownerId;
}