package com.Ecommerce.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.Entity.Facture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface FactureRepositry extends JpaRepository<Facture,Long> {
  //  @Query("SELECT f.produit, COUNT(f) FROM Facture f WHERE YEAR(f.dateFact) = :year GROUP BY f.produit ORDER BY COUNT(f) DESC")
  //  List<Object[]> findTopSellingProductsByYear(@Param("year") int year, Pageable pageable);
}
