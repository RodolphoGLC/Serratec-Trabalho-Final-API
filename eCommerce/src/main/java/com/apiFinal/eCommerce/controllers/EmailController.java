package com.apiFinal.eCommerce.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apiFinal.eCommerce.dtos.EmailDTO;
import com.apiFinal.eCommerce.entities.Email;
import com.apiFinal.eCommerce.services.EmailService;

import jakarta.validation.Valid;

@RestController
public class EmailController {

	@Autowired
	EmailService emailService;

	@PostMapping("/email")
	public ResponseEntity<Email> saveEmail(@RequestBody @Valid EmailDTO emailDto) {
		Email email = new Email();
		BeanUtils.copyProperties(emailDto, email);
		emailService.saveEmail(email);
		return new ResponseEntity<>(email, HttpStatus.CREATED);
	}
/*
	@GetMapping
	public ResponseEntity<List<Email>> getAllEmails() {
		return new ResponseEntity<>(emailService.getAllEmails(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Email> getEmailById(@PathVariable Integer id) {
		return new ResponseEntity<>(emailService.getEmailById(id), HttpStatus.OK);
	}


	@PutMapping
	public ResponseEntity<Email> updateEmail(@RequestBody Email email,Integer id) {
		return new ResponseEntity<>(emailService.saveEmail(email),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteEmail(@PathVariable Integer id) {
		Boolean resp = emailService.deleteEmail(id);
		if(resp == true) {
			return new ResponseEntity<>(resp,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
		}
	}
	*/
	
}
