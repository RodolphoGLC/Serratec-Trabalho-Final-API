package com.apiFinal.eCommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiFinal.eCommerce.entities.Role;
import com.apiFinal.eCommerce.entities.RoleEnum;



@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(RoleEnum name);
}