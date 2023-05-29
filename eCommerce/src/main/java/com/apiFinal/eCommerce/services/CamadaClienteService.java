package com.apiFinal.eCommerce.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.dto.CriacaoClienteDTO;
import com.apiFinal.eCommerce.dto.RealizarPedidoDTO;
import com.apiFinal.eCommerce.dto.RelatorioPedidoDTO;
import com.apiFinal.eCommerce.entities.Cliente;
import com.apiFinal.eCommerce.entities.Endereco;
import com.apiFinal.eCommerce.entities.ItemPedido;
import com.apiFinal.eCommerce.entities.Pedido;
import com.apiFinal.eCommerce.entities.Produto;
import com.apiFinal.eCommerce.exceptions.InsufficientException;
import com.apiFinal.eCommerce.exceptions.NoSuchElementException;
import com.apiFinal.eCommerce.repositories.ClienteRepository;
import com.apiFinal.eCommerce.repositories.EnderecoRepository;
import com.apiFinal.eCommerce.repositories.ItemPedidoRepository;
import com.apiFinal.eCommerce.repositories.PedidoRepository;
import com.apiFinal.eCommerce.repositories.ProdutoRepository;

@Service
public class CamadaClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
//	@Autowired
//	RoleRepository roleRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	RelatorioPedidoService relatorioPedidoService;
	
	@Autowired
	RelatorioPedidoDTO relatorioPedidoDTO;
	
	
	public Boolean criarConta(CriacaoClienteDTO criacaoClienteDTO) {
		try {
			//roleRepository
			Cliente cliente = new Cliente();
			Endereco endereco = new Endereco();
			
			cliente.setCpf(criacaoClienteDTO.getCpf());
			cliente.setDataNascimento(criacaoClienteDTO.getDataNascimento());
			cliente.setEmail(criacaoClienteDTO.getEmail());
			cliente.setTelefone(criacaoClienteDTO.getTelefone());
			endereco.setCep(criacaoClienteDTO.getCep());
			//API Externa para Rua,Bairro, Cidade, UF
			endereco.setRua("Rua A");
			endereco.setBairro("Bairro A");
			endereco.setCidade("Cidade A");
			endereco.setUf("RJ");
			// Tem que trocar
			endereco.setNumero(criacaoClienteDTO.getNumero());
			if (criacaoClienteDTO.getComplemento() != null) {
				endereco.setComplemento(criacaoClienteDTO.getComplemento());
			}
			endereco.setCliente(cliente);
			clienteRepository.save(cliente);
			enderecoRepository.save(endereco);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Boolean fazerPedido(List<RealizarPedidoDTO> lista, Integer idCliente) {
			Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new NoSuchElementException("cliente", idCliente));
			Pedido pedido = new Pedido();
			List<ItemPedido> listaItemPedido = new ArrayList<ItemPedido>();
			Double valor = 0.0;
			for(RealizarPedidoDTO pedidoProdutoDTO: lista) {
				Integer id = pedidoProdutoDTO.getIdProduto();
				Produto produto = produtoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("produto", id));
				if(produto.getQtdEstoque() > pedidoProdutoDTO.getQuantidade()) {
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setQuantidade(pedidoProdutoDTO.getQuantidade());
					itemPedido.setPrecoVenda(produto.getValorUnitario());
					itemPedido.setPorcentagemDesconto(pedidoProdutoDTO.getPorcentagemDesconto());
					itemPedido.setProduto(produto);
					itemPedido.setValorBruto(produto.getValorUnitario() * itemPedido.getQuantidade());
					itemPedido.setValorLiquido(itemPedido.getValorBruto() * (1-itemPedido.getPorcentagemDesconto()));
					listaItemPedido.add(itemPedido);
					valor += itemPedido.getValorLiquido();
				} else {
					throw new InsufficientException(id, produto.getNome());
				}
			}
			
			pedido.setCliente(cliente);
			pedido.setDataPedido(new Date());
			pedido.setListaItemPedido(listaItemPedido);
			pedido.setStatus("Em separação.");
			pedido.setValorTotal(valor);
			pedidoRepository.save(pedido);
			
			RelatorioPedidoDTO relatorio = relatorioPedidoService.gerarRelatorio(pedido);
			emailService.enviarEmail(pedido.getCliente().getEmail(), "Nota fiscal do pedido", relatorio.notaFiscal(pedido));
		
		return true;
	}
	
	
	
}
