package com.apiFinal.eCommerce.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.dto.ItemPedidoDTO;
import com.apiFinal.eCommerce.dto.PedidoDTO;
import com.apiFinal.eCommerce.dto.RelatorioPedidoDTO;
import com.apiFinal.eCommerce.entities.ItemPedido;
import com.apiFinal.eCommerce.entities.Pedido;
import com.apiFinal.eCommerce.exceptions.NoSuchElementException;
import com.apiFinal.eCommerce.exceptions.UniqueElementException;
import com.apiFinal.eCommerce.exceptions.UnmatchingIdsException;
import com.apiFinal.eCommerce.repositories.ItemPedidoRepository;
import com.apiFinal.eCommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	RelatorioPedidoService relatorioPedidoService;
	
	
	public List<Pedido> getAllPedidos() {
		return pedidoRepository.findAll();
	}
	
	public Pedido getPedidoById(Integer id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("pedido", id));
	}
	
	public Pedido savePedido(Pedido pedido) {
		if (pedido.getIdPedido() == null) {
				pedido.setStatus("separação");
				LocalDateTime data = LocalDateTime.now();
				pedido.setDataPedido(data);
				Double valor = 0.0;
				if(pedido.getListaItemPedido() != null) {
					for(ItemPedido itemPedido: pedido.getListaItemPedido()) {
						ItemPedido itemPedidoCheck = itemPedidoRepository.findById(itemPedido.getIdItemPedido()).orElseThrow(() -> new NoSuchElementException("itemPedido", itemPedido.getIdItemPedido()));
						valor += itemPedidoCheck.getValorLiquido();
					}					
				}
				pedido.setValorTotal(valor);
				if (pedido.getValorTotal() != 0) {
					PedidoDTO pedidoDTO =new PedidoDTO();
					List<ItemPedidoDTO> lista = new ArrayList<ItemPedidoDTO>();
					pedidoDTO.setIdPedido(pedido.getIdPedido());
					pedidoDTO.setDataPedido(pedido.getDataPedido());
					pedidoDTO.setStatus(pedido.getStatus());
					pedidoDTO.setValorTotal(pedido.getValorTotal());
					
					for (ItemPedido itemPedido:pedido.getListaItemPedido()) {
						ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
						itemPedidoDTO.setIdProduto(itemPedido.getProduto().getIdProduto());
						itemPedidoDTO.setNomeProduto(itemPedido.getProduto().getNome());
						itemPedidoDTO.setPrecoVenda(itemPedido.getProduto().getValorUnitario());
						itemPedidoDTO.setQuantidade(itemPedido.getQuantidade());
						itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
						itemPedidoDTO.setPercentualDesconto(itemPedido.getPorcentagemDesconto());
						itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
						lista.add(itemPedidoDTO);
						}
					pedidoDTO.setListaItemPedido(lista);
					RelatorioPedidoDTO relatorioPedidoDTO = new RelatorioPedidoDTO();
					emailService.enviarEmail(pedido.getCliente().getEmail(), "pedido", relatorioPedidoDTO.notaFiscal(pedidoDTO));
					
				}
				return pedidoRepository.save(pedido);
				
		} else {
			throw new UnmatchingIdsException(pedido.getIdPedido(), pedido.getStatus());
		}
	}
	
	public Pedido updatePedido(Pedido pedido) {
		Integer id = pedido.getIdPedido();
		if (id == null) {
			throw new UnmatchingIdsException();
		} else {
			if (pedidoRepository.findById(id).orElse(null) != null) {
				try {
					return pedidoRepository.save(pedido);		
				} catch (Exception e) {
					throw new UniqueElementException();
				}					
			} else {
				throw new UnmatchingIdsException(id);
			}
		}
	}
	
	public Boolean deletePedido(Integer id) {
		Pedido pedidoDeletada = pedidoRepository.findById(id).orElse(null);
		if(pedidoDeletada != null) {
			pedidoRepository.deleteById(id);
			return true;
		} else {
			throw new UnmatchingIdsException(id);
		}
	}
	
}
