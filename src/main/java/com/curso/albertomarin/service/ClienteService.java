package com.curso.albertomarin.service;

import com.curso.albertomarin.model.Cliente;
import com.curso.albertomarin.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Capa de SERVICIO (negocio) que coordina operaciones CRUD sobre clientes
 * Recibe llamadas desde Controller, llama a Repository, maneja lógica de negocio
 */
@Service // ← Spring crea esta clase automáticamente como "bean" singleton
public class ClienteService implements IClienteService {

    // Spring inyecta automáticamente el repositorio JPA al iniciar la app
    @Autowired // ← Dependency Injection (no "new Repository()")
    private IClienteRepository clienteRepository;

    /**
     * CREA nuevo cliente en base de datos
     * @param cliente objeto Cliente con datos (ID debe ser null)
     * @return Cliente guardado con ID autogenerado
     */
    @Override
    public Cliente create(Cliente cliente) {
        // repository.save() hace INSERT SQL automáticamente via Hibernate
        return clienteRepository.save(cliente);
    }

    /**
     * ACTUALIZA cliente existente
     * @param cliente objeto Cliente con ID válido (modifica campos)
     * @return Cliente actualizado
     */
    @Override
    public Cliente update(Cliente cliente) {
        // Mismo save(): Hibernate detecta ID existente → UPDATE SQL
        return clienteRepository.save(cliente);
    }

    /**
     * BUSCA cliente por ID
     * @param id clave primaria del cliente
     * @return Cliente o null si no existe
     */
    @Override
    public Cliente findById(Integer id) {
        // findById() devuelve Optional (puede ser vacío)
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        // orElse(null) → null si no existe (podrías lanzar excepción)
        return clienteOptional.orElse(null);
    }

    /**
     * LISTA TODOS los clientes
     * @return Lista completa de clientes
     */
    @Override
    public List<Cliente> findAll() {
        // SELECT * FROM cliente ejecutado automáticamente
        return clienteRepository.findAll();
    }

    /**
     * ELIMINA cliente por ID
     * @param id clave primaria a eliminar
     */
    @Override
    public void delete(Integer id) {
        // DELETE FROM cliente WHERE id=? ejecutado automáticamente
        clienteRepository.deleteById(id);
    }
}
