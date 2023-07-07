package com.banco.bancospring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.bancospring2.model.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer>{

}
