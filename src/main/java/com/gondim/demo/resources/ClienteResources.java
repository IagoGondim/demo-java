package com.gondim.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gondim.demo.domain.Cliente;
import com.gondim.demo.services.ClienteService;

@RestControllerAdvice
@RequestMapping(value = "/clientes")
public class ClienteResources {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
