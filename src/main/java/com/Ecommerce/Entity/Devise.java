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
public class Devise implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long IdDev;
    private String code;
    private String symbole;
    private Double Tauxchange;

    @OneToMany(mappedBy = "devise")
    @JsonIgnore
    private Set<Facture> factures = new HashSet<>();
}
