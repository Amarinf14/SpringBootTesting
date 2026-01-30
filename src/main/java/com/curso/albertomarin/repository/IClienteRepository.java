package com.curso.albertomarin.repository;

import com.curso.albertomarin.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
}
