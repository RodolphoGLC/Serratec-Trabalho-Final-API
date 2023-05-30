package com.apiFinal.eCommerce.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class CriacaoClienteDTO {
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String username;
	
	@NotNull
	private String senha;
	
	@NotNull
	private String cpf;
	
	@NotNull
	private String telefone;
	
	@NotNull
	private LocalDateTime dataNascimento;
	
	@NotNull
	private String cep;
	
	@NotNull
	private String numero;
	
	private String complemento;

	public CriacaoClienteDTO() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
}
