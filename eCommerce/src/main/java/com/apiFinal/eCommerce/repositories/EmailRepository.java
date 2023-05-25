package com.apiFinal.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.constraints.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{

}
