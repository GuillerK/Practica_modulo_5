package com.banco.bancospring2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.bancospring2.model.Gestor;

public interface GestorRepository extends JpaRepository<Gestor, Integer>{
	
	public Optional<Gestor> findFirstByCorreo(String correo);
	
	public Optional<Gestor> findFirstByCorreoAndPassword (String correo, String password);
	
}
