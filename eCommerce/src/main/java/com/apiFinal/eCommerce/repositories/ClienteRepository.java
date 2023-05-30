package com.apiFinal.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiFinal.eCommerce.entities.Cliente;

public interface ClienteRepository 
			extends JpaRepository<Cliente, Integer> {

	public Cliente findByEmail(String email);
}