package br.com.condomineolite.dtos.person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequestDTO {
    private String name;
    private String email;
    private String phone;
}
