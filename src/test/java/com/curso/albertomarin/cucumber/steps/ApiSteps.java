package com.curso.albertomarin.cucumber.steps;

import com.curso.albertomarin.model.Cliente;
import com.curso.albertomarin.service.IClienteService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApiSteps {

    @Autowired
    private TestRestTemplate restTemplate; // Cliente HTTP de Spring

    private Cliente clienteTemporal; // Variable para recordar los datos del cliente entre un paso y otro
    private Integer idGenerado; // Guardamos el ID que nos devuelve la base de datos tras el POST
    private ResponseEntity<?> ultimaRespuesta; // Guarda la respuesta completa (status, body, headers) del último paso ejecutado

    // --- CREATE (POST) ---
    @When("registro un nuevo cliente con nombre {string} y email {string}")
    public void registro_un_nuevo_cliente(String nombre, String email) {
        // 1. Preparamos el objeto Java con los datos que recibimos de Cucumber
        Cliente c = new Cliente();
        c.setNombre(nombre);
        c.setApellidos("López");
        c.setDireccion("Dirección");
        c.setTelefono("8888888");
        c.setEmail(email);

        // 2. Ejecutamos un POST real a la URL "/clientes"
        ResponseEntity<Cliente> response = restTemplate.postForEntity("/clientes", c, Cliente.class);

        // 3. Extraemos los resultados para usarlos en los siguientes pasos
        clienteTemporal = response.getBody();
        idGenerado = (clienteTemporal != null) ? clienteTemporal.getIdCliente() : null;
        ultimaRespuesta = response;
    }

    @Then("el cliente es guardado exitosamente")
    public void el_cliente_es_guardado_exitosamente() {
        // Verificamos que el controlador devolvió un código 201 (Created)
        assertEquals(HttpStatus.CREATED, ultimaRespuesta.getStatusCode());
        // Verificamos que la base de datos realmente generó un ID
        assertNotNull(idGenerado);
    }

    // --- READ (GET - FindAll) ---
    @Then("el cliente aparece en la lista total de clientes")
    public void el_cliente_aparece_en_la_lista_total() {
        // Llamamos al GET general. TestRestTemplate mapea el JSON recibido a un array de Clientes
        Cliente[] todosArray = restTemplate.getForObject("/clientes", Cliente[].class);
        List<Cliente> todos = Arrays.asList(todosArray);

        // Comprobamos mediante Streams si el ID que creamos antes está en la lista que devuelve el servidor
        boolean existe = todos.stream().anyMatch(c -> c.getIdCliente().equals(idGenerado));
        assertTrue(existe, "El cliente debería estar en la lista de todos");
    }

    // --- UPDATE (PUT) ---
    @When("actualizo el nombre del cliente creado a {string} y su email a {string}")
    public void actualizo_el_nombre_del_cliente(String nuevoNombre, String nuevoEmail) {
        // Modificamos el objeto que teníamos en memoria
        clienteTemporal.setNombre(nuevoNombre);
        clienteTemporal.setEmail(nuevoEmail);

        // Enviamos el objeto actualizado mediante PUT.
        restTemplate.put("/clientes", clienteTemporal);
    }

    @Then("el cliente con ID guardado tiene como nombre {string}")
    public void el_cliente_tiene_como_nombre(String nombreEsperado) {
        // Hacemos un GET por ID para refrescar los datos desde la base de datos
        Cliente c = restTemplate.getForObject("/clientes/" + idGenerado, Cliente.class);
        assertNotNull(c);
        // Comprobamos que el nombre en la BD coincide con el nombre actualizado
        assertEquals(nombreEsperado, c.getNombre());
    }

    // --- DELETE ---
    @When("elimino el cliente creado")
    public void elimino_el_cliente_creado() {
        // Ejecutamos la llamada DELETE al endpoint específico
        restTemplate.delete("/clientes/" + idGenerado);
    }

    @Then("el cliente ya no existe en el sistema")
    public void el_cliente_ya_no_existe(){
        // Al intentar buscar un cliente que acabamos de borrar, el sistema debería fallar
        ResponseEntity<Cliente> response = restTemplate.getForEntity("/clientes/" + idGenerado, Cliente.class);

        // Si el Controller lanza la ModelNotFoundException correctamente, recibiremos un 404 (Not Found)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // --- EXCEPTION ---
    @When("elimino un cliente con ID {int}")
    public void elimino_un_cliente_con_id(Integer id) {
        // exchange() se usa aquí porque permite capturar la respuesta de un DELETE (que restTemplate.delete no devuelve)
        // Forzamos un borrado de un ID que no existe para que salte la excepción personalizada
        ultimaRespuesta = restTemplate.exchange("/clientes/" + id, org.springframework.http.HttpMethod.DELETE, null, Void.class);
    }

    @Then("el sistema responde con un error {int}")
    public void el_sistema_responde_con_un_error(Integer codigo) {
        // Verificamos que el código de error coincide con el esperado
        assertEquals(codigo, ultimaRespuesta.getStatusCode().value());
    }
}