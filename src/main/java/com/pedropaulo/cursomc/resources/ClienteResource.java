package com.pedropaulo.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedropaulo.cursomc.domain.Cliente;
import com.pedropaulo.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes") // Responde por este endPoint
public class ClienteResource {
	
	@Autowired
	private ClienteService service;

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find (@PathVariable Integer id) {
		
		Cliente categoria1 = service.buscar(id);
		return ResponseEntity.ok().body(categoria1);
	}



}
