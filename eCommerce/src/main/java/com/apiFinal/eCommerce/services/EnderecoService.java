package com.apiFinal.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.dto.APIEnderecoDTO;
import com.apiFinal.eCommerce.entities.Endereco;
import com.apiFinal.eCommerce.exceptions.NoSuchElementException;
import com.apiFinal.eCommerce.exceptions.UniqueElementException;
import com.apiFinal.eCommerce.exceptions.UnmatchingIdsException;
import com.apiFinal.eCommerce.repositories.ClienteRepository;
import com.apiFinal.eCommerce.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	APIEnderecoDTO apiEnderecoDTO;
	
	public List<Endereco> getAllEnderecos() {
		return enderecoRepository.findAll();
	}

	public Endereco getEnderecoById(Integer id) {
		return enderecoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Categoria", id));
	}

	public Endereco saveEndereco(Endereco endereco) {
		if (endereco.getIdEndereco() == null) {
			try {
				APIEnderecoDTO enderecoDTO = apiEnderecoDTO.consultarEndereco(endereco.getCep());
				endereco.setBairro(enderecoDTO.getBairro());
				endereco.setCidade(enderecoDTO.getLocalidade());
				endereco.setRua(enderecoDTO.getLogradouro());
				endereco.setUf(enderecoDTO.getUf());
				if(endereco.getComplemento() == null) {
					endereco.setComplemento("S/C");
				}
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
