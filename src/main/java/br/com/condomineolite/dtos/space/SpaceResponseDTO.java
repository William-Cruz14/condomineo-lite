package br.com.condomineolite.dtos.space;

import br.com.condomineolite.models.Space;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpaceResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Integer capacity;

    public SpaceResponseDTO(Space space) {
        this.id = space.getId();
        this.name = space.getName();
        this.description = space.getDescription();
        this.capacity = space.getCapacity();
    }
}