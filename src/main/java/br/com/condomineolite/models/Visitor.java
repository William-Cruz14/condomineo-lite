package br.com.condomineolite.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("VISITANTE")
@Data
@EqualsAndHashCode(callSuper = true)
public class Visitor extends Person {

    @OneToMany(
            mappedBy = "visitor", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<VisitRecord> visits = new ArrayList<>();
}
