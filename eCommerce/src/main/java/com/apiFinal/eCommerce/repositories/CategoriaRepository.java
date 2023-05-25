package com.apiFinal.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiFinal.eCommerce.entities.Categoria;

public interface CategoriaRepository 
			extends JpaRepository<Categoria, Integer> {

		
}
