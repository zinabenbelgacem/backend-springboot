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
public class ReglementFacture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRF;
    private double montant;

    @ManyToOne
    @JoinColumn(name = "idFact")
    private Facture facturesr;

    @ManyToOne
    @JoinColumn(name = "Idreg")
    private Reglement reglement;
}
