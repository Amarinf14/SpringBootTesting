# Cliente Management API

![Banner API](http://googleusercontent.com/image_collection/image_retrieval/13524848555152263167_0)

[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white&style=for-the-badge)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.2-brightgreen?logo=springboot&logoColor=white&style=for-the-badge)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?logo=postgresql&logoColor=white&style=for-the-badge)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apachemaven&logoColor=white&style=for-the-badge)](https://maven.apache.org/)
[![Cucumber](https://img.shields.io/badge/Cucumber-BDD-00a818?logo=cucumber&logoColor=white&style=for-the-badge)](https://cucumber.io/)

Sistema robusto de gestión de clientes desarrollado bajo la arquitectura **RESTful** con Spring Boot. Este proyecto implementa un ciclo de vida completo de datos (CRUD) utilizando una arquitectura de capas y un enfoque estricto en la calidad de software mediante **BDD (Behavior Driven Development)**.

---

## Tabla de Contenidos
1. [Arquitectura y Tecnologías](#arquitectura-y-tecnologías)
2. [Requisitos Previos](#requisitos-previos)
3. [Configuración e Instalación](#configuración-e-instalación)
4. [Documentación de la API](#documentación-de-la-api)
5. [Calidad y Testing (BDD)](#calidad-y-testing)
6. [Manejo de Errores](#manejo-de-errores)

---

## Arquitectura y Tecnologías

La aplicación sigue los principios de diseño de Spring Boot para asegurar la escalabilidad:

* **Java 21**: Uso de las últimas funcionalidades del lenguaje.
* **Spring Boot 3.2.2**: Framework base para la creación de microservicios.
* **Spring Data JPA**: Abstracción de persistencia con Hibernate.
* **PostgreSQL**: Base de datos relacional para entornos de producción.
* **Cucumber & JUnit 5**: Frameworks para pruebas de aceptación y tests unitarios.
* **Jacoco**: Herramienta para la medición de cobertura de código.



---

## Requisitos Previos

* **JDK 21** o superior.
* **Maven 3.9+**.
* **PostgreSQL** instanciado y en ejecución.

---

## Configuración e Instalación

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/tu-usuario/curso-springboot-clientes.git](https://github.com/tu-usuario/curso-springboot-clientes.git)
    cd curso-springboot-clientes
    ```

2.  **Configurar base de datos:**
    Modifica `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/curso_db
    spring.datasource.username=postgres
    spring.datasource.password=tu_password
    ```

3.  **Compilar y Ejecutar:**
    ```bash
    mvn spring-boot:run
    ```

---

## Documentación de la API

### Endpoints de Clientes
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/clientes` | Retorna todos los clientes registrados. |
| `GET` | `/clientes/{id}` | Retorna un cliente por su ID único. |
| `POST` | `/clientes` | Crea un nuevo cliente en el sistema. |
| `PUT` | `/clientes` | Actualiza los datos de un cliente existente. |
| `DELETE` | `/clientes/{id}` | Elimina un cliente de la base de datos. |

---

## Calidad y Testing (BDD)

El proyecto destaca por su alto nivel de confianza gracias al uso de **Cucumber**. Las pruebas no solo validan la lógica, sino el comportamiento esperado del negocio.

### Ejecución de Tests
Para lanzar los tests y generar el reporte de cobertura:
```bash
mvn clean verify
```

### Escenarios de Prueba
Los tests están escritos en lenguaje natural (Gherkin) en la ruta `src/test/resources/features`:
```gherkin
Scenario: Ciclo de vida completo de un cliente
  When registro un nuevo cliente con nombre "Alberto" y email "alberto@gmail.com"
  Then el cliente es guardado exitosamente
  And el cliente aparece en la lista total de clientes
```

### Cobertura de Código
Gracias a la implementación de `TestRestTemplate`, garantizamos una cobertura del 100% en:
* **Controllers:** Validación de endpoints y respuestas HTTP.
* **Exceptions:** Asegurando que las excepciones como `ModelNotFoundException` se disparen correctamente ante IDs inexistentes.

---

## Manejo de Errores
La API utiliza un `@ControllerAdvice` para centralizar las excepciones. Las respuestas de error siguen el estándar:
```json
{
  "timestamp": "2026-02-03T13:00:00",
  "message": "El cliente con el ID proporcionado no existe",
  "details": "uri=/clientes/999"
}
```