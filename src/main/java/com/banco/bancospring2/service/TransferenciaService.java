package com.banco.bancospring2.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.bancospring2.model.Cliente;
import com.banco.bancospring2.model.Transferencia;
import com.banco.bancospring2.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

	@Autowired
	TransferenciaRepository transferenciaRepository;

	@Autowired
	ClienteService clienteService;
	
	
	public ArrayList<Transferencia> buscarTransferenciasPorOrdenante(Integer id) {
	    return this.transferenciaRepository.findByOrdenanteId(id);
	}
	
	public ArrayList<Transferencia> buscarTransferenciasPorBeneficiario(Integer id) {
	    return this.transferenciaRepository.findByBeneficiarioId(id);
	}

	public ArrayList<Transferencia> leerTransferencias() {
		return (ArrayList<Transferencia>) this.transferenciaRepository.findAll();
	}
	
	public Optional<Transferencia> buscarTransferencia(Integer id){
		return this.transferenciaRepository.findById(id);	
		}
	
	public void borrarTransferenciaPorId(Integer Id) {
		this.transferenciaRepository.deleteById(Id);
	}
	
	public Transferencia guardarTransferencia(Transferencia transferencia) {
		this.transferenciaRepository.save(transferencia);
		Cliente ordenante = transferencia.getOrdenante();
		
		ordenante = clienteService.leerCliente(ordenante.getId()).orElse(null);
		Double saldoOrdenante = ordenante.getSaldo();
		
		Cliente beneficiario = transferencia.getBeneficiario();
		beneficiario = clienteService.leerCliente(beneficiario.getId()).orElse(null);
		Double saldoBeneficiario = beneficiario.getSaldo();
		Double importe = transferencia.getImporte();
		
		ordenante.setSaldo(saldoOrdenante - importe);
		beneficiario.setSaldo(saldoBeneficiario + importe);
		
		clienteService.guardarClienteSinActualizarPassword(ordenante);
		clienteService.guardarClienteSinActualizarPassword(beneficiario);
		return this.transferenciaRepository.save(transferencia);
	}
	

	
}