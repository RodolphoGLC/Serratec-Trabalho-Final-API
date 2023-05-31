package com.apiFinal.eCommerce.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.apiFinal.eCommerce.entities.Categoria;
import com.apiFinal.eCommerce.entities.Produto;
import com.apiFinal.eCommerce.exceptions.NoSuchElementException;
import com.apiFinal.eCommerce.exceptions.UniqueElementException;
import com.apiFinal.eCommerce.exceptions.UnmatchingIdsException;
import com.apiFinal.eCommerce.repositories.CategoriaRepository;
import com.apiFinal.eCommerce.repositories.ProdutoRepository;

import io.jsonwebtoken.io.IOException;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public List<Produto> getAllProdutos() {
		return produtoRepository.findAll();
	}

	public Produto getProdutoById(Integer id) {
		return produtoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("produto", id));
	}

	public Produto saveProduto(MultipartFile image, String nome, String descricao, Integer qtdEstoque, Double valorUnitario, Integer idCategoria) {
		Produto produto = new Produto();
		if (produto.getIdProduto() == null) {
			try {
				produto.setNome(nome);
				produto.setDescricao(descricao);
				produto.setQtdEstoque(qtdEstoque);
				produto.setValorUnitario(valorUnitario);
				if(categoriaRepository.findById(idCategoria).orElse(null) != null) {
					Categoria categoria = categoriaRepository.findById(idCategoria).orElse(null);
					produto.setCategoria(categoria);
				}
				produto.setDataCadastro(LocalDateTime.now());
				try {
					byte[] img = null;
					img = image.getBytes();
					produto.setImage(img);	
				} catch (IOException e) {
				}
				
				return produtoRepository.save(produto);
			} catch (Exception e) {
				throw new UniqueElementException();
			}
		} else {
			throw new UnmatchingIdsException(produto.getIdProduto(), produto.getNome());
		}
	}

	public Produto updateProduto(MultipartFile image, Integer idProduto, String nome, String descricao, Integer qtdEstoque, Double valorUnitario, Integer idCategoria) {
		if (idProduto == null) {
			throw new UnmatchingIdsException();
		} else {
			Produto produto = produtoRepository.findById(idProduto).orElse(null);
			if (produtoRepository.findById(idProduto).orElse(null) != null) {
				try {
					produto.setNome(nome);
					produto.setDescricao(descricao);
					produto.setQtdEstoque(qtdEstoque);
					produto.setValorUnitario(valorUnitario);
					produto.setCategoria(categoriaRepository.findById(idCategoria).orElse(null));
					produto.setDataCadastro(LocalDateTime.now());
					try {
						byte[] img = null;
						img = image.getBytes();
						produto.setImage(img);	
					} catch (IOException e) {
					}
					
					return produtoRepository.save(produto);
				} catch (Exception e) {
					throw new UniqueElementException();
				}
			} else {
				throw new UnmatchingIdsException(idProduto);
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
