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
import com.apiFinal.eCommerce.repositories.ClienteRepository;
import com.apiFinal.eCommerce.repositories.ItemPedidoRepository;
import com.apiFinal.eCommerce.repositories.PedidoRepository;
import com.apiFinal.eCommerce.repositories.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
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
		Boolean check = false;
		try {
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
				
				check = true;
				return pedidoRepository.save(pedido);
			} else {
				throw new UnmatchingIdsException(pedido.getIdPedido(), pedido.getStatus());
			}
		} catch (Exception e) {
			throw new UnmatchingIdsException(pedido.getIdPedido(), pedido.getStatus());
		} finally {
			if(check) {
				if (pedido.getValorTotal() != 0) {
					PedidoDTO pedidoDTO =new PedidoDTO();
					List<ItemPedidoDTO> lista = new ArrayList<ItemPedidoDTO>();
					pedidoDTO.setIdPedido(pedido.getIdPedido());
					pedidoDTO.setDataPedido(pedido.getDataPedido());
					pedidoDTO.setStatus(pedido.getStatus());
					pedidoDTO.setValorTotal(pedido.getValorTotal());
					
					for (ItemPedido itemPedido: pedido.getListaItemPedido()) {
						Integer idIP = itemPedido.getIdItemPedido();
						if (itemPedidoRepository.findById(idIP).orElse(null) != null && itemPedidoRepository.findById(idIP).orElse(null).getProduto() != null) {
							ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
							itemPedidoDTO.setIdProduto(itemPedidoRepository.findById(idIP).orElse(null).getProduto().getIdProduto());
							itemPedidoDTO.setNomeProduto(itemPedidoRepository.findById(idIP).orElse(null).getProduto().getNome());
							itemPedidoDTO.setPrecoVenda(itemPedidoRepository.findById(idIP).orElse(null).getProduto().getValorUnitario());
							itemPedidoDTO.setQuantidade(itemPedidoRepository.findById(idIP).orElse(null).getQuantidade());
							itemPedidoDTO.setValorBruto(itemPedidoRepository.findById(idIP).orElse(null).getValorBruto());
							itemPedidoDTO.setPercentualDesconto(itemPedidoRepository.findById(idIP).orElse(null).getPorcentagemDesconto());
							itemPedidoDTO.setValorLiquido(itemPedidoRepository.findById(idIP).orElse(null).getValorLiquido());
							lista.add(itemPedidoDTO);
						}
					}	
					pedidoDTO.setListaItemPedido(lista);
					RelatorioPedidoDTO relatorioPedidoDTO = new RelatorioPedidoDTO();
					Integer id = pedido.getCliente().getIdCliente();
					String email = clienteRepository.findById(id).orElse(null).getEmail();
					emailService.enviarEmail(email, "Nota fiscal do pedido", relatorioPedidoDTO.notaFiscal(pedidoDTO));
					
				}
			}
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
