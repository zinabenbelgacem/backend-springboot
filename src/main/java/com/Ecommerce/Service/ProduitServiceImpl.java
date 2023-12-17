package com.Ecommerce.Service;

import com.Ecommerce.Entity.Produit;
import com.Ecommerce.Entity.client;
import com.Ecommerce.Repositry.ProduitRepositry;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService{
    private ProduitRepositry produitRepositry;


}
