package com.Ecommerce.Repositry;
import com.Ecommerce.Entity.Facture;
import com.Ecommerce.Entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Ecommerce.Entity.client;

import java.util.List;

@Repository
public interface ClientRepositry extends JpaRepository<client,Long>{
	client findByNom(String nom);
	@Query("SELECT c FROM client c WHERE c.idClient = :idClient")
	client getclientById(@Param("idClient") Long idClient);
	@Query("SELECT f FROM Facture f JOIN f.reglementFactures rf WHERE f.client.idClient = :idClient")
	List<Facture> findFacturesRegleesPourClient(Long idClient);
	@Query("SELECT f FROM Facture f LEFT JOIN f.reglementFactures rf WHERE f.client.idClient = :idClient AND rf.idRF IS NULL")
	List<Facture> findFacturesNonRegleesPourClient(Long idClient);


}
