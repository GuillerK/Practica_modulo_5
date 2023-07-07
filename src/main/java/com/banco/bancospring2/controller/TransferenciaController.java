package com.banco.bancospring2.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.bancospring2.model.Transferencia;
import com.banco.bancospring2.service.TransferenciaService;

//En desarrollo // http://localhost:8080/transferencia
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	TransferenciaService transferenciaService;

	@GetMapping()
	public ArrayList<Transferencia> obtenerTransferencias() {
		return this.transferenciaService.leerTransferencias();
	}
	
	@GetMapping(path ="/{id}")
	public Optional<Transferencia> obtenerTransferencia(@PathVariable("id") Integer id) {
		return this.transferenciaService.buscarTransferencia(id);
	}
	
	@GetMapping(path = "/ordenante/{idOrdenante}")
	public ArrayList<Transferencia> obtenerTransferenciaPorOrdenante(@PathVariable("idOrdenante") Integer id) {
	    return this.transferenciaService.buscarTransferenciasPorOrdenante(id);
	}
	
	@GetMapping(path="/beneficiario/{idBeneficiario}")
	public ArrayList<Transferencia> obtenerTransferenciaPorBeneficiario(@PathVariable("idBeneficiario") Integer id) {
	    return this.transferenciaService.buscarTransferenciasPorBeneficiario(id);
	}

	@PostMapping
	public Transferencia guardarTransferencia(@RequestBody Transferencia transferencia) {
		return this.transferenciaService.guardarTransferencia(transferencia);
	}
	
	@DeleteMapping(path="/{id}")
	public void borrarTransferencia(@PathVariable("id") Integer id) {
		this.transferenciaService.borrarTransferenciaPorId(id);
	}

}
