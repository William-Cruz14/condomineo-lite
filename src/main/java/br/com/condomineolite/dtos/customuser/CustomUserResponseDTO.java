package br.com.condomineolite.dtos.customuser;

import br.com.condomineolite.models.CustomUser;
import br.com.condomineolite.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CustomUserResponseDTO {

    private Long id;
    private String username;
    private Set<String> roles;

    public CustomUserResponseDTO(CustomUser customUser) {
        this.id = customUser.getId();
        this.username = customUser.getUsername();
        this.roles = customUser.getRoles().stream()
                .map(Role::getName)
                .collect(java.util.stream.Collectors.toSet());
    }
}
