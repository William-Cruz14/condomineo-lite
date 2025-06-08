package br.com.condomineolite.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@DiscriminatorValue("SINDICO")
@EqualsAndHashCode(callSuper = true)
public class Trustee extends Person {
    @Column(nullable = false, unique = true)
    private String document;
}
