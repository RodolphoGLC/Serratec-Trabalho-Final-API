package com.apiFinal.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiFinal.eCommerce.entities.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long>{

}
