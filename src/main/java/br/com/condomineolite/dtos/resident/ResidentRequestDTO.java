package br.com.condomineolite.dtos.resident;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String document;
}