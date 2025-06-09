package br.com.condomineolite.dtos.trustee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrusteeRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String document;
}
