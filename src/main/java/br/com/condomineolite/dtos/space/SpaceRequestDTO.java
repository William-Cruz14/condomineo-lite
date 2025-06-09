package br.com.condomineolite.dtos.space;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpaceRequestDTO {
    private String name;
    private String description;
    private Integer capacity;
}
