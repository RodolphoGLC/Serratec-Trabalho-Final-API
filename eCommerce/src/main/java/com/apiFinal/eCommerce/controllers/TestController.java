package com.apiFinal.eCommerce.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('INSTRUTOR') or hasRole('DIRETORIA')")  
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/inst")
	@PreAuthorize("hasRole('INSTRUTOR')")
	public String moderatorAccess() {
		return "Instrutor Board.";
	}

	@GetMapping("/adm")
	@PreAuthorize("hasRole('DIRETORIA')")
	public String adminAccess() {
		return "Admin Board.";
	}
}