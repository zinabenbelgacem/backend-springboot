package com.Ecommerce.Controlleur;

import com.Ecommerce.Entity.Produit;
import com.Ecommerce.Service.ProduitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class produitControlleur {
    private  ProduitService produitService;
    /*@GetMapping("/produits-plus-vendus")
    public List<Produit> obtenirProduitsLesPlusVendus(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return produitService.obtenirProduitsLesPlusVendus(page, size);
    }*/
    //@GetMapping("/produits-rupture-stock")
   // public List<Produit> obtenirProduitsEnRuptureDeStock() {
      //  return produitService.obtenirProduitsEnRuptureDeStock();
   // }
}
