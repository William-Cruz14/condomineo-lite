package br.com.condomineolite.dtos.customuser;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CustomUserCreateDTO {

    private String username;
    private String password;
    private Set<String> roles;
}
