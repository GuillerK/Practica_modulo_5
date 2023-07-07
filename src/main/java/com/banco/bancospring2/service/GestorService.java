package com.banco.bancospring2.service;

import org.springframework.stereotype.Service;

import com.banco.bancospring2.model.Gestor;
import com.banco.bancospring2.repository.GestorRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class GestorService {

	@Autowired
	GestorRepository gestorRepository;
	
	public ArrayList<Gestor> leerGestores(){
		return(ArrayList<Gestor>) this.gestorRepository.findAll();
	}
	
	public Optional<Gestor> leerGestorPorId(Integer id){
		return this.gestorRepository.findById(id);	}
	
	public Gestor guardarGestor(Gestor gestor) {
		String pass = gestor.getPassword();
		if (pass!=null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			pass = encoder.encode(pass);
			gestor.setPassword(pass);
		} else {
			Gestor gestorExistente = leerGestorPorId(gestor.getId()).orElse(null);
			if(gestorExistente!=null) {
				gestor.setPassword(gestorExistente.getPassword());
			}
		}
		return this.gestorRepository.save(gestor);
	}


	public void borrarGestorPorId(Integer Id) {
		this.gestorRepository.deleteById(Id);
	}
	
	public Optional<Gestor> buscarGestorPorCorreo(String correo) {
		return this.gestorRepository.findFirstByCorreo(correo);
	}
	
	public Optional<Gestor> buscarGestorPorCorreoYPass(String correo, String password) {
		Optional<Gestor> gestor = buscarGestorPorCorreo(correo);
		if (gestor.isPresent()) {
			Gestor gestorEncontrado = gestor.get();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(password, gestorEncontrado.getPassword())) {
				return gestor;
			}
		}
		return null;
	}
	
	

}
