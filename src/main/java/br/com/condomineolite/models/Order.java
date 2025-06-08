package br.com.condomineolite.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String description;

    private Boolean received;

    private Boolean delivered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "resident_id", nullable = false)
    private Resident resident;

}
