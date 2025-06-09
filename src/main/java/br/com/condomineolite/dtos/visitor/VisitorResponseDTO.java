package br.com.condomineolite.dtos.visitor;

import br.com.condomineolite.models.Visitor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VisitorResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;

    public VisitorResponseDTO(Visitor visitor) {
        this.id = visitor.getId();
        this.name = visitor.getName();
        this.email = visitor.getEmail();
        this.phone = visitor.getPhone();
    }
}
