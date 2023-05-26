package com.apiFinal.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.Endereco;
import com.apiFinal.eCommerce.exceptions.UniqueElementException;
import com.apiFinal.eCommerce.exceptions.UnmatchingIdsException;
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
		if (endereco.getIdEndereco() == null) {
			try {
				return enderecoRepository.save(endereco);
			} catch (Exception e) {
				throw new UniqueElementException();
			}
		} else {
			throw new UnmatchingIdsException(endereco.getIdEndereco(), endereco.getCep());
		}
	}

	public Endereco updateEndereco(Endereco endereco) {
		Integer id = endereco.getIdEndereco();
		if (id == null) {
			throw new UnmatchingIdsException();
		} else {
			if (enderecoRepository.findById(id).orElse(null) != null) {
				try {
					return enderecoRepository.save(endereco);
				} catch (Exception e) {
					throw new UniqueElementException();
				}
			} else {
				throw new UnmatchingIdsException(id);
			}
		}
	}

	public Boolean deleteEndereco(Integer id) {
		Endereco enderecoDeletada = enderecoRepository.findById(id).orElse(null);
		if (enderecoDeletada != null) {
			enderecoRepository.deleteById(id);
			return true;
		} else {
			throw new UnmatchingIdsException(id);
		}
	}

}
