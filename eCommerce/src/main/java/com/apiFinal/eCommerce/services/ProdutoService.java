package com.apiFinal.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.Produto;
import com.apiFinal.eCommerce.exceptions.UniqueElementException;
import com.apiFinal.eCommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Produto> getAllProdutos() {
		return produtoRepository.findAll();
	}
	
	public Produto getProdutoById(Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
	public Produto saveProduto(Produto produto) {
		try {
			return produtoRepository.save(produto);		
		} catch (Exception e) {
			throw new UniqueElementException();
		}
	}
	
	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Boolean deleteProduto(Integer id) {
		produtoRepository.deleteById(id);
		Produto produtoDeletado = produtoRepository.findById(id).orElse(null);
		if(produtoDeletado == null) {
			return true;
		} else {
			return false;
		}
	}
	
}
