package com.apiFinal.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiFinal.eCommerce.entities.Pedido;

public interface PedidoRepository 
			extends JpaRepository<Pedido, Integer> {

		
}