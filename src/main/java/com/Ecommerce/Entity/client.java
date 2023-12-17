package com.Ecommerce.Entity;
import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class client implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClient;
	private String codecl;
	private String nom;
	private String prenom;
	private String adresse;
	private Boolean actif;
	private Integer tel;
	private String email;

	@OneToMany(mappedBy = "client")
	@JsonIgnore
	private Set<Facture> factures = new HashSet<>();
public  client(int idClient,String codecl,String nom,String prenom,String adresse,Boolean actif,Integer tel,String email){
	this.idClient= (long) idClient;
	this.codecl=codecl;
	this.email=email;
	this.actif=actif;
	this.nom=nom;
	this.prenom=prenom;
	this.adresse=adresse;
	this.tel=tel;
}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getCodecl() {
		return codecl;
	}

	public void setCodecl(String codecl) {
		this.codecl = codecl;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public Integer getTel() {
		return tel;
	}

	public void setTel(Integer tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Facture> getFactures() {
		return factures;
	}

	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}
}
