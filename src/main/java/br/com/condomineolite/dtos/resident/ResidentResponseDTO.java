package br.com.condomineolite.dtos.resident;

import br.com.condomineolite.models.Resident;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResidentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;

    public ResidentResponseDTO(Resident resident) {
        this.id = resident.getId();
        this.name = resident.getName();
        this.email = resident.getEmail();
        this.phone = resident.getPhone();
        this.document = resident.getDocument();
    }

    public ResidentResponseDTO() {
    }
}
