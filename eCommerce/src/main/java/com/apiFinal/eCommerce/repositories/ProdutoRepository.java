package com.apiFinal.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiFinal.eCommerce.entities.Produto;

public interface ProdutoRepository 
			extends JpaRepository<Produto, Integer> {

		
}