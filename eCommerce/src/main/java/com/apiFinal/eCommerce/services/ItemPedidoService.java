package com.apiFinal.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.ItemPedido;
import com.apiFinal.eCommerce.entities.Produto;
import com.apiFinal.eCommerce.exceptions.InsufficientException;
import com.apiFinal.eCommerce.exceptions.NoSuchElementException;
import com.apiFinal.eCommerce.exceptions.UniqueElementException;
import com.apiFinal.eCommerce.exceptions.UnmatchingIdsException;
import com.apiFinal.eCommerce.repositories.ItemPedidoRepository;
import com.apiFinal.eCommerce.repositories.ProdutoRepository;

@Service
public class ItemPedidoService {


	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<ItemPedido> getAllItemPedidos() {
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido getItemPedidoById(Integer id) {
		return itemPedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("item-pedido", id));
	}
	
	public ItemPedido saveItemPedido(ItemPedido itemPedido) {
		if (itemPedido.getIdItemPedido() == null) {
			Integer idProduto = itemPedido.getProduto().getIdProduto();
			Produto produto = produtoRepository.findById(idProduto).orElse(null);
			if(produto.getQtdEstoque() >= itemPedido.getQuantidade()) {
				itemPedido.setValorBruto(itemPedido.getQuantidade() * produto.getValorUnitario());
				itemPedido.setValorLiquido(itemPedido.getValorBruto()*(1-itemPedido.getPorcentagemDesconto()));
				itemPedido.setPrecoVenda(produto.getValorUnitario());
				produto.setQtdEstoque(produto.getQtdEstoque() - itemPedido.getQuantidade());
				produtoRepository.save(produto);
				return itemPedidoRepository.save(itemPedido);			
			} else {
				throw new InsufficientException(produto.getNome());
			}	
		} else {
			throw new UnmatchingIdsException(itemPedido.getIdItemPedido(), itemPedido.toString());
		}
	}
	
	public ItemPedido updateItemPedido(ItemPedido itemPedido) {
		Integer id = itemPedido.getIdItemPedido();
		if (id == null) {
			throw new UnmatchingIdsException();
		} else {
			if (itemPedidoRepository.findById(id).orElse(null) != null) {
				try {
					return itemPedidoRepository.save(itemPedido);		
				} catch (Exception e) {
					throw new UniqueElementException();
				}					
			} else {
				throw new UnmatchingIdsException(id);
			}
		}
	}
	
	public Boolean deleteItemPedido(Integer id) {
		ItemPedido itemPedidoDeletada = itemPedidoRepository.findById(id).orElse(null);
		if(itemPedidoDeletada != null) {
			itemPedidoRepository.deleteById(id);
			return true;
		} else {
			throw new UnmatchingIdsException(id);
		}
	}
	
}
