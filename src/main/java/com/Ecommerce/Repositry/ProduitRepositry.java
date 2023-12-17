package com.Ecommerce.Repositry;

import com.Ecommerce.Entity.client;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Ecommerce.Entity.Produit;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ProduitRepositry extends JpaRepository<Produit,Long>{

  //  @Query("SELECT p FROM Produit p WHERE p.qteStock = 0")
  //  List<Produit> findOutOfStockProducts();

}
