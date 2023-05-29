package com.apiFinal.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.Cliente;
import com.apiFinal.eCommerce.exceptions.NoSuchElementException;
import com.apiFinal.eCommerce.exceptions.UniqueElementException;
import com.apiFinal.eCommerce.exceptions.UnmatchingIdsException;
import com.apiFinal.eCommerce.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	public Cliente getClienteById(Integer id) {
		return clienteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("cliente", id));
	}

	public Cliente saveCliente(Cliente cliente) {
		if (cliente.getIdCliente() == null) {
			try {
				return clienteRepository.save(cliente);
			} catch (Exception e) {
				throw new UniqueElementException();
			}
		} else {
			throw new UnmatchingIdsException(cliente.getIdCliente(), cliente.getCpf());
		}
	}

	public Cliente updateCliente(Cliente cliente) {
		Integer id = cliente.getIdCliente();
		if (id == null) {
			throw new UnmatchingIdsException();
		} else {
			if (clienteRepository.findById(id).orElse(null) != null) {
				try {
					return clienteRepository.save(cliente);
				} catch (Exception e) {
					throw new UniqueElementException();
				}
			} else {
				throw new UnmatchingIdsException(id);
			}
		}
	}

	public Boolean deleteCliente(Integer id) {
		Cliente clienteDeletada = clienteRepository.findById(id).orElse(null);
		if (clienteDeletada != null) {
			clienteRepository.deleteById(id);
			return true;
		} else {
			throw new UnmatchingIdsException(id);
		}
	}

}
