package com.Ecommerce.Service;
import java.util.*;
import java.util.stream.Collectors;

import com.Ecommerce.Entity.DetailFacture;
import com.Ecommerce.Entity.Facture;
import com.Ecommerce.Entity.ReglementFacture;
import com.Ecommerce.Repositry.FactureRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Ecommerce.Entity.client;
import com.Ecommerce.Repositry.ClientRepositry;
import java.time.LocalDate;

@Service
public class ClientServiceImpl implements ClientService{
	@Autowired
	ClientRepositry clientRep;

	@Override
	public client addclient(client cl) {
		return clientRep.save(cl);
	}

	@Override
	public List<client> addListclient(List<client> listclient) {

		return clientRep.saveAll(listclient);
	}

	@Override
	public client Updateclient(client cl, Long idClient) {
		client usr=clientRep.findById(idClient).get();
		usr.setNom(cl.getNom());
		usr.setPrenom(cl.getPrenom());
		usr.setAdresse(cl.getAdresse());
		usr.setEmail(cl.getEmail());
		usr.setTel(cl.getTel());
		usr.setActif(cl.getActif());
		usr.setCodecl(cl.getCodecl());
		return clientRep.save(usr);
	}

	@Override
	public String deleteclient(Long idClient) {
		String ch="";
		clientRep.deleteById(idClient);
		 ch="client deleteed succefuly !!!";
		 return ch;
	}

	@Override
	public List<client> getallclients() {
		return clientRep.findAll();
	}

	@Override
	public client findByNom(String nom) {
		return clientRep.findByNom(nom);
	}

	@Override
	public client getclientById(Long idClient) {
		return clientRep.getclientById(idClient);
	}
	@Override
	public Map<String, Long> getMostPurchasedProducts(client client) {
		Set<Facture> clientFactures = client.getFactures();
		Map<String, Long> productCounts = new HashMap<>();
		for (Facture facture : clientFactures) {
			Set<DetailFacture> detailFactures = facture.getDetailFactures();
			for (DetailFacture detailFacture : detailFactures) {
				String productCode = detailFacture.getProduit().getCodep();
				productCounts.put(productCode, productCounts.getOrDefault(productCode, 0L) + 1);
			}
		}
		return productCounts;
	}
	@Override
	public List<Facture> getFacturesRegleesPourClient(Long idClient) {
		return clientRep.findFacturesRegleesPourClient( idClient);
	}
@Override
	public List<Facture> getFacturesNonRegleesPourClient(Long idClient) {
		return clientRep.findFacturesNonRegleesPourClient(idClient);
	}

	@Override
	public double calculerChiffreAffairesGlobal(Long clientId) {
		client client = clientRep.findById(clientId).orElse(null);
		if (client == null) {
			return 0.0;
		}
		double chiffreAffairesTotal = 0.0;
		Set<Facture> factures = client.getFactures();

		for (Facture facture : factures) {
			chiffreAffairesTotal += calculerMontantTotal(facture);
		}

		return chiffreAffairesTotal;
	}
	@Override
	public double calculerChiffreAffairesParAn(Long clientId, int annee) {
		client client = clientRep.getclientById(clientId);
		if (client == null) {
			return 0.0;
		}
		double chiffreAffairesAnnee = 0.0;
		Set<Facture> factures = client.getFactures();

		for (Facture facture : factures) {
			if (isFacturePourAnnee(facture, annee)) {
				chiffreAffairesAnnee += calculerMontantTotal(facture);
			}
		}
		System.out.println("Chiffre d'affaires pour l'année " + annee + "clientId  "+clientId +"  :"+ chiffreAffairesAnnee);
		return chiffreAffairesAnnee;
	}

	public double calculerMontantTotal(Facture facture) {
		double montantTotal = 0.0;
		Set<DetailFacture> detailFactures = facture.getDetailFactures();

		for (DetailFacture detailFacture : detailFactures) {
			montantTotal += detailFacture.getQte() * detailFacture.getPrixunitaire();
		}

		return montantTotal;
	}
	public boolean isFacturePourAnnee(Facture facture, int annee) {
		String dateFacture = facture.getDateFact();

		if (dateFacture != null && !dateFacture.isEmpty()) {
			// une date au format "YYYY-MM-DD"
			String[] parts = dateFacture.split("-"); // "YYYY-MM-DD"

			if (parts.length > 0) {
				int anneeFacture = Integer.parseInt(parts[0]);
				return anneeFacture == annee;
			}
		}

		return false;
	}
	@Override
	public double calculerResteGlobalMontantsNonPayes(Long idClient) {
		client client = clientRep.findById(idClient).orElse(null);
		if (client == null) {
			return 0.0;
		}
		double montantRestantGlobal = 0.0;
		List<Facture> facturesNonReglees = getFacturesNonRegleesPourClient(idClient);

		for (Facture facture : facturesNonReglees) {
			double montantTotalFacture = calculerMontantTotal(facture);
			double montantTotalPaye = calculerMontantTotalPaye(facture);

			double montantRestant = montantTotalFacture - montantTotalPaye;

			if (montantRestant > 0) {
				montantRestantGlobal += montantRestant;
			}
		}

		return montantRestantGlobal;
	}

	public double calculerMontantTotalPaye(Facture facture) {
		double montantTotalPaye = 0.0;

		for (ReglementFacture reglementFacture : facture.getReglementFactures()) {
			montantTotalPaye += reglementFacture.getReglement().getMontantPayer();
		}

		return montantTotalPaye;
	}
}
