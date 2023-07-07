package com.banco.bancospring2.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.banco.bancospring2.model.Cliente;
import com.banco.bancospring2.model.Gestor;
import com.banco.bancospring2.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public ArrayList<Cliente> leerClientes(){
		return(ArrayList<Cliente>) this.clienteRepository.findAll();
	}
	
	public Optional<Cliente> leerCliente(Integer id){
		return this.clienteRepository.findById(id);	
		}
	
	public String obtenerPasswordActual (Cliente cliente) {
		Cliente clienteGuardado = leerCliente(cliente.getId()).orElse(null);
		if (clienteGuardado != null) {
			return clienteGuardado.getPassword();
			}
		return null;
	}
	
	public Cliente guardarClienteSinActualizarPassword(Cliente cliente) {
		String passGuardada = obtenerPasswordActual(cliente);
		cliente.setPassword(passGuardada);
		return this.clienteRepository.save(cliente);
	}
	
	public Cliente guardarCliente(Cliente cliente) {
		String pass = cliente.getPassword();
		if (pass!=null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			pass = encoder.encode(pass);
			cliente.setPassword(pass);
		} else {
			Cliente clienteExistente = leerCliente(cliente.getId()).orElse(null);
			if(clienteExistente!=null) {
				cliente.setPassword(clienteExistente.getPassword());
			}
		}
		return this.clienteRepository.save(cliente);
	}
	
	public void borrarClientePorId(Integer Id) {
		this.clienteRepository.deleteById(Id);
	}
	
	public Optional<Cliente> buscarClientePorCorreo(String correo) {
		return this.clienteRepository.findFirstByCorreo(correo);
	}
	
	public Optional<Cliente> buscarClientePorCorreoYPass(String correo, String password) {
		Optional<Cliente> cliente = buscarClientePorCorreo(correo);
		if (cliente.isPresent()) {
			Cliente clienteEncontrado = cliente.get();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(password, clienteEncontrado.getPassword())) {
				return cliente;
			}
		}
		return null;
	}
	
}
