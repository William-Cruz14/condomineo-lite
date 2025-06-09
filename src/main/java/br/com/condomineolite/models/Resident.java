package br.com.condomineolite.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MORADOR")
@Data
@EqualsAndHashCode(callSuper = true )
public class Resident  extends Person {

    @Column(nullable = false, unique = true)
    private String document;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricHouse> historicHouses = new ArrayList<>();

    @OneToMany(
            mappedBy = "resident", cascade = CascadeType.PERSIST,
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<Order> orders = new ArrayList<>();

    @OneToMany(
            mappedBy = "resident", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<VisitRecord> visitsReceiveds = new ArrayList<>();

    @OneToMany(
            mappedBy = "resident", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY
    )
    private List<Reserve> reserves = new ArrayList<>();



}
