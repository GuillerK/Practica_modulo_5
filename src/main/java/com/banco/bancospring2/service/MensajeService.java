package com.banco.bancospring2.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.bancospring2.model.Mensaje;
import com.banco.bancospring2.repository.MensajeRepository;


@Service
public class MensajeService {

	@Autowired
	MensajeRepository mensajeRepository;
	
	public ArrayList<Mensaje> leerMensaje(){
		return(ArrayList<Mensaje>) this.mensajeRepository.findAll();
	}
	
	public Optional<Mensaje> leerMensaje(Integer id){
		return this.mensajeRepository.findById(id);	}
	
	public Mensaje guardarMensaje(Mensaje mensaje) {
		return this.mensajeRepository.save(mensaje);
	}
	
	public void borrarMensajePorId(Integer Id) {
		this.mensajeRepository.deleteById(Id);
	}
	
//	public Optional<Mensaje> buscarMensajePorCorreo(String correo) {
//		return this.mensajeRepository.findFirstByCorreo(correo);
//	}
//	
//	public Optional<Mensaje> buscarMensajePorCorreoYPass(String correo, String password) {
//		return this.mensajeRepository.findFirstByCorreoAndPassword(correo, password);
//	}
	
	
}
