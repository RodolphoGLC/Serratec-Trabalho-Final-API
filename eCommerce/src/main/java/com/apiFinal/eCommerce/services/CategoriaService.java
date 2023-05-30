package com.apiFinal.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.Categoria;
import com.apiFinal.eCommerce.exceptions.NoSuchElementException;
import com.apiFinal.eCommerce.exceptions.UniqueElementException;
import com.apiFinal.eCommerce.exceptions.UnmatchingIdsException;
import com.apiFinal.eCommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> getAllCategorias() {
		return categoriaRepository.findAll();
	}
	
	public Categoria getCategoriaById(Integer id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("categoria", id));
	}
	
	public Categoria saveCategoria(Categoria categoria) {
		if (categoria.getIdCategoria() == null) {
			try {
				return categoriaRepository.save(categoria);		
			} catch (Exception e) {
				throw new UniqueElementException();
			}			
		} else {
			throw new UnmatchingIdsException(categoria.getIdCategoria(), categoria.getNome());
		}
	}
	
	public Categoria updateCategoria(Categoria categoria) {
		Integer id = categoria.getIdCategoria();
		if (id == null) {
			throw new UnmatchingIdsException();
		} else {
			if (categoriaRepository.findById(id).orElse(null) != null) {
				try {
					return categoriaRepository.save(categoria);		
				} catch (Exception e) {
					throw new UniqueElementException();
				}					
			} else {
				throw new NoSuchElementException("categoria", categoria.getIdCategoria());
			}
		}
	}
	
	public Boolean deleteCategoria(Integer id) {
		Categoria categoriaDeletada = categoriaRepository.findById(id).orElse(null);
		if(categoriaDeletada != null) {
			categoriaRepository.deleteById(id);
			return true;
		} else {
			throw new UnmatchingIdsException(id);
		}
	}
	
}
