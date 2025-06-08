package br.com.condomineolite.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "vehicles")
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String plate;

    private String model;

    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person owner;
}
