Feature: Gestión completa de Clientes API
  Scenario: Ciclo de vida completo de un cliente
    # CREATE
    When registro un nuevo cliente con nombre "Marín" y email "marin.test@gmail.com"
    Then el cliente es guardado exitosamente

    # READ (FindAll)
    And el cliente aparece en la lista total de clientes

    # UPDATE
    When actualizo el nombre del cliente creado a "Pedro" y su email a "pedro.test@gmail.com"
    Then el cliente con ID guardado tiene como nombre "Pedro"

    # DELETE
    When elimino el cliente creado
    Then el cliente ya no existe en el sistema

    # EXCEPTION
    When elimino un cliente con ID 9999
    Then el sistema responde con un error 404
