package com.apiFinal.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.Produto;
import com.apiFinal.eCommerce.exceptions.UniqueElementException;
import com.apiFinal.eCommerce.exceptions.UnmatchingIdsException;
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
		if (produto.getIdProduto() == null) {
			try {
				return produtoRepository.save(produto);
			} catch (Exception e) {
				throw new UniqueElementException();
			}
		} else {
			throw new UnmatchingIdsException(produto.getIdProduto(), produto.getNome());
		}
	}

	public Produto updateProduto(Produto produto) {
		Integer id = produto.getIdProduto();
		if (id == null) {
			throw new UnmatchingIdsException();
		} else {
			if (produtoRepository.findById(id).orElse(null) != null) {
				try {
					return produtoRepository.save(produto);
				} catch (Exception e) {
					throw new UniqueElementException();
				}
			} else {
				throw new UnmatchingIdsException(id);
			}
		}
	}

	public Boolean deleteProduto(Integer id) {
		Produto produtoDeletada = produtoRepository.findById(id).orElse(null);
		if (produtoDeletada != null) {
			produtoRepository.deleteById(id);
			return true;
		} else {
			throw new UnmatchingIdsException(id);
		}
	}

}
