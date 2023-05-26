package com.apiFinal.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.Cliente;
import com.apiFinal.eCommerce.exceptions.UniqueElementException;
import com.apiFinal.eCommerce.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}
	
	public Cliente getClienteById(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	public Cliente saveCliente(Cliente cliente) {
		try {
			return clienteRepository.save(cliente);		
		} catch (Exception e) {
			throw new UniqueElementException();
		}
	}
	
	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Boolean deleteCliente(Integer id) {
		clienteRepository.deleteById(id);
		Cliente clienteDeletado = clienteRepository.findById(id).orElse(null);
		if(clienteDeletado == null) {
			return true;
		} else {
			return false;
		}
	}
	
}
