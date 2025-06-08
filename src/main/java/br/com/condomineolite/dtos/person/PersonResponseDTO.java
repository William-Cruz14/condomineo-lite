package br.com.condomineolite.dtos.person;

import br.com.condomineolite.models.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseDTO {
    private Long id;
    private String name;
    private String phone;

    public PersonResponseDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.phone = person.getPhone();

    }
}
