package com.apiFinal.eCommerce.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPedido", scope = Pedido.class)
@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer idPedido;
	
	//O dia e a hora da realização do pedido
	@Column(name = "data_pedido")
	private Date dataPedido;
	
	//Deve haver a condição que seja depois da data de envio
	@Column(name = "data_entrega")
	private Date dataEntrega;
	
	//Deve haver a condição que seja depois da data do pedido
	@Column(name = "data_envio")
	private Date dataEnvio;
	
	@NotNull
	@Column(name = "status")
	private String status;
	
	@Column(name = "valor_total")
	private double valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", unique = true)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> listaItemPedido;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getListaItemPedido() {
		return listaItemPedido;
	}

	public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
		this.listaItemPedido = listaItemPedido;
	}
}
