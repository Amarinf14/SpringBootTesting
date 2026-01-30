package com.curso.albertomarin.service;

import com.curso.albertomarin.model.Cliente;

import java.util.List;

public interface IClienteService {

    Cliente create(Cliente cliente);

    Cliente update(Cliente cliente);

    Cliente findById(Integer id);

    List<Cliente> findAll();

    void delete(Integer id);
}
