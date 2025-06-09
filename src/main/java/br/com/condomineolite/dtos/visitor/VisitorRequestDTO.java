package br.com.condomineolite.dtos.visitor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitorRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String document;
}