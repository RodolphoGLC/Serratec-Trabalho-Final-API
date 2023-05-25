package com.apiFinal.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiFinal.eCommerce.entities.Endereco;

public interface EnderecoRepository 
			extends JpaRepository<Endereco, Integer> {

		
}