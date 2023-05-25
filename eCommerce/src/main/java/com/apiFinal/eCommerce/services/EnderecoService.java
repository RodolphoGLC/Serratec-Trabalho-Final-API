package com.apiFinal.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.Endereco;
import com.apiFinal.eCommerce.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	public List<Endereco> getAllEnderecos() {
		return enderecoRepository.findAll();
	}
	
	public Endereco getEnderecoById(Integer id) {
		return enderecoRepository.findById(id).orElse(null);
	}
	
	public Endereco saveEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public Endereco updateEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public void deleteEndereco(Integer id) {
		enderecoRepository.deleteById(id);
	}
	
}
