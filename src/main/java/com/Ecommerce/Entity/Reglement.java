package com.Ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reglement implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Idreg;
    private Long MontantPayer;
    private String Date;

    @OneToMany(mappedBy = "reglement")
    @JsonIgnore
    private Set<ReglementFacture> reglementFactures = new HashSet<>();

}
