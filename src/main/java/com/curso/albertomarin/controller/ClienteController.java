package com.curso.albertomarin.controller;

import com.curso.albertomarin.exception.ModelNotFoundException;
import com.curso.albertomarin.model.Cliente;
import com.curso.albertomarin.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping
    private ResponseEntity<List<Cliente>> findAllClients() {
        return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.create(cliente), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateCliente(@RequestBody Cliente cliente) {
        clienteService.update(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findByIdCliente(@PathVariable("id") Integer idCliente) {
        Cliente cliente = clienteService.findById(idCliente);
        if (cliente == null) {
            throw new ModelNotFoundException("Cliende con ID (" + idCliente + ") no encontrado");
        }

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteByIdCliente(@PathVariable("id") Integer idCliente) {

        Cliente cliente = clienteService.findById(idCliente);
        if (cliente == null) {
            throw new ModelNotFoundException("Cliende con ID (" + idCliente + ") no encontrado");

            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliende con ID (" + idCliente + ") no encontrado");

            // throw new Exception("El cliente que desea eliminar no existe");
        }

        clienteService.delete(idCliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
