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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banco.bancospring2.model.Cliente;
import com.banco.bancospring2.service.ClienteService;


@RestController
// En desarrollo // http://localhost:8080/cliente
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

		@Autowired
		ClienteService clienteService;

		@GetMapping()
		public ArrayList<Cliente> obtenerClientes() {
			return this.clienteService.leerClientes();
		}
		
		@GetMapping(path ="/correo/{email}")
		public Optional<Cliente> obtenerClientePorCorreo(@PathVariable("email") String correo) {
			return this.clienteService.buscarClientePorCorreo(correo);
		}
		
		@GetMapping(path ="/login")
		public Optional<Cliente> loguearCliente(@RequestParam("correo") String correo, @RequestParam("password") String password) {
			return this.clienteService.buscarClientePorCorreoYPass(correo, password);
		}
		
		@GetMapping(path ="/{id}")
		public Optional<Cliente> obtenerCliente(@PathVariable("id") Integer id) {
			return this.clienteService.leerCliente(id);
		}

		@PostMapping
		public Cliente guardarCliente(@RequestBody Cliente cliente) {
			return this.clienteService.guardarCliente(cliente);
		}
		
		@DeleteMapping(path="/{id}")
		public void borrarCliente(@PathVariable("id") Integer id) {
			this.clienteService.borrarClientePorId(id);
		}
		

}
