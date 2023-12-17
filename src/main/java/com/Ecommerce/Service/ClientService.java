package com.Ecommerce.Service;

import java.util.List;
import java.util.Map;

import com.Ecommerce.Entity.Facture;
import com.Ecommerce.Entity.Produit;
import com.Ecommerce.Entity.client;


public interface ClientService {
	 public client addclient(client cl);
	 public List<client> addListclient(List<client> listclient);
	 public client Updateclient(client cl, Long idClient);
	 public String deleteclient(Long idClient);
	 public List<client> getallclients();
	 public client findByNom(String nom);
	 public client getclientById(Long idClient);
	public Map<String, Long> getMostPurchasedProducts(client client);
	public List<Facture> getFacturesRegleesPourClient(Long idClient);
	public List<Facture> getFacturesNonRegleesPourClient(Long idClient);
	public double calculerChiffreAffairesGlobal(Long clientId);
	public double calculerChiffreAffairesParAn(Long clientId, int annee);
	public double calculerResteGlobalMontantsNonPayes(Long idClient);
}
