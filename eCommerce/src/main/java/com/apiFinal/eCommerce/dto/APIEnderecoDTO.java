package com.apiFinal.eCommerce.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class APIEnderecoDTO {
	
	private String uf;
	private String localidade;
	private String bairro;
	private String logradouro;
	
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	
	public APIEnderecoDTO consultarEndereco(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://viacep.com.br/ws/{cep}/json/";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);
		
		APIEnderecoDTO apiLogradouroDTO = restTemplate.getForObject(uri, APIEnderecoDTO.class, params);
	
		return apiLogradouroDTO;
	}
}
