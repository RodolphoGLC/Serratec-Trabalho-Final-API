package com.apiFinal.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.Categoria;
import com.apiFinal.eCommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> getAllCategorias() {
		return categoriaRepository.findAll();
	}
	
	public Categoria getCategoriaById(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria updateCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Boolean deleteCategoria(Integer id) {
		categoriaRepository.deleteById(id);
		return true;
	}
	
}
