package com.apiFinal.eCommerce.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.dto.APIEnderecoDTO;
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
@Component
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
	
	@Autowired
	APIEnderecoDTO apiEnderecoDTO;
	
	
	public Boolean criarConta(CriacaoClienteDTO criacaoClienteDTO) {
			//roleRepository
			Cliente cliente = new Cliente();
			Endereco endereco = new Endereco();
			//User user = new User();
			
			//Criação local de usuário
			
			cliente.setCpf(criacaoClienteDTO.getCpf());
			cliente.setDataNascimento(criacaoClienteDTO.getDataNascimento());
			cliente.setEmail(criacaoClienteDTO.getEmail());
			cliente.setTelefone(criacaoClienteDTO.getTelefone());
			
			//API Externa para Rua,Bairro, Cidade, UF
			APIEnderecoDTO endereco1 = apiEnderecoDTO.consultarEndereco(criacaoClienteDTO.getCep());
			
			endereco.setCep(criacaoClienteDTO.getCep());
			endereco.setComplemento(criacaoClienteDTO.getComplemento());
			endereco.setNumero(criacaoClienteDTO.getNumero());
			endereco.setBairro(endereco1.getBairro());
			endereco.setCidade(endereco1.getLocalidade());
			endereco.setRua(endereco1.getLogradouro());
			endereco.setUf(endereco1.getUf());
			
			enderecoRepository.save(endereco);
			
			cliente.setEndereco(endereco);
			
			clienteRepository.save(cliente);
			
			return true;
		
	}
	
//	public Boolean fazerPedido(List<RealizarPedidoDTO> lista, Integer idCliente) {
//			Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new NoSuchElementException("cliente", idCliente));
//			Pedido pedido = new Pedido();
//			List<ItemPedido> listaItemPedido = new ArrayList<ItemPedido>();
//			Double valor = 0.0;
//			for(RealizarPedidoDTO pedidoProdutoDTO: lista) {
//				Integer id = pedidoProdutoDTO.getIdProduto();
//				Produto produto = produtoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("produto", id));
//				if(produto.getQtdEstoque() > pedidoProdutoDTO.getQuantidade()) {
//					ItemPedido itemPedido = new ItemPedido();
//					itemPedido.setQuantidade(pedidoProdutoDTO.getQuantidade());
//					itemPedido.setPrecoVenda(produto.getValorUnitario());
//					itemPedido.setPorcentagemDesconto(pedidoProdutoDTO.getPorcentagemDesconto());
//					itemPedido.setProduto(produto);
//					itemPedido.setValorBruto(produto.getValorUnitario() * itemPedido.getQuantidade());
//					itemPedido.setValorLiquido(itemPedido.getValorBruto() * (1-itemPedido.getPorcentagemDesconto()));
//					listaItemPedido.add(itemPedido);
//					valor += itemPedido.getValorLiquido();
//				} else {
//					throw new InsufficientException(id, produto.getNome());
//				}
//			}
//			LocalDateTime data = LocalDateTime.now();
//			pedido.setCliente(cliente);
//			pedido.setDataPedido(data);
//			pedido.setListaItemPedido(listaItemPedido);
//			pedido.setStatus("Em separação.");
//			pedido.setValorTotal(valor);
//			pedidoRepository.save(pedido);
//			
//			RelatorioPedidoDTO relatorio = relatorioPedidoService.gerarRelatorio(pedido);
//			emailService.enviarEmail(pedido.getCliente().getEmail(), "Nota fiscal do pedido", relatorio.notaFiscal(pedido));
//		
//		return true;
//	}
	
	
	
}
