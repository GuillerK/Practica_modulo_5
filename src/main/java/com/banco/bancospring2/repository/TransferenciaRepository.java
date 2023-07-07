package com.banco.bancospring2.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.bancospring2.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository <Transferencia, Integer>{

	ArrayList<Transferencia> findByBeneficiarioId(Integer id);

	ArrayList<Transferencia> findByOrdenanteId(Integer id);

}