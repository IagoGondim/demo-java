package com.gondim.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gondim.demo.domain.Pedido;
import com.gondim.demo.services.PedidoService;

@RestControllerAdvice
@RequestMapping(value = "/pedidos")
public class PedidoResources {

	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Pedido obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
