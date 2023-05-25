package com.apiFinal.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiFinal.eCommerce.entities.ItemPedido;

public interface ItemPedidoRepository 
			extends JpaRepository<ItemPedido, Integer> {

		
}