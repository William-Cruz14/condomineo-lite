package br.com.condomineolite.dtos.trustee;

import br.com.condomineolite.models.Trustee;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrusteeResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;

    public TrusteeResponseDTO(Trustee trustee) {
        this.id = trustee.getId();
        this.name = trustee.getName();
        this.email = trustee.getEmail();
        this.phone = trustee.getPhone();
        this.document = trustee.getDocument();
    }
}
