package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.model.Pedido; // Asegúrate de que esta importación exista
import com.techlab.ecommerce.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping; // Importante para manejar POST requests
import org.springframework.web.bind.annotation.RequestBody; // Importante para leer el JSON del cuerpo de la solicitud
import org.springframework.web.bind.annotation.RequestMapping; // Para el mapeo base de la URL
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; // Indica que es un controlador REST
import org.springframework.web.bind.annotation.PathVariable; // Importa la anotación PathVariable
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación GetMapping
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin; // Importa CrossOrigin para manejar CORS

@RestController // Marca esta clase como un controlador REST
@RequestMapping("/api/pedidos") // Define la ruta base para todos los endpoints en este controlador
@CrossOrigin(origins = "*") // O puedes poner "*" para permitir desde cualquier origen para desarrollo
// Para desarrollo, puedes usar origins = "*" para permitir cualquier origen.
// Para producción, debes especificar el dominio exacto de tu frontend.
public class PedidoController {

    // Inyección de dependencias del PedidoService
    private final PedidoService pedidoService; // Nombre de variable en minúscula

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    /**
     * Endpoint para crear un nuevo pedido.
     * Maneja las solicitudes POST a /api/pedidos.
     *
     * @param nuevoPedido El objeto Pedido enviado en el cuerpo de la solicitud (JSON).
     * @return ResponseEntity con el Pedido creado y el estado HTTP 201 Created si es exitoso,
     * o un mensaje de error con un estado HTTP 400 Bad Request o 500 Internal Server Error.
     */
    @PostMapping // Esta anotación mapea las solicitudes POST a la ruta base ("/api/pedidos")
    public ResponseEntity<?> crearPedido(@RequestBody Pedido nuevoPedido) {
        try {
            // Llama al método del servicio para crear el pedido
            Pedido pedidoCreado = pedidoService.crearPedido(nuevoPedido);
            // Si todo sale bien, devuelve el pedido creado con un estado 201 Created
            return new ResponseEntity<>(pedidoCreado, HttpStatus.CREATED);
        } catch (StockInsuficienteException e) {
            // Captura la excepción de stock insuficiente y devuelve un 400 Bad Request
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            // Captura otras excepciones de validación (ej. pedido sin líneas) y devuelve un 400 Bad Request
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada y devuelve un 500 Internal Server Error
            // Es buena práctica loguear estas excepciones en el servidor
            System.err.println("Error inesperado al crear el pedido: " + e.getMessage());
            e.printStackTrace(); // Imprime el stack trace para depuración
            return new ResponseEntity<>("Error interno del servidor al crear el pedido: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Aquí puedes añadir otros métodos para manejar otras operaciones con pedidos,
    // como obtener todos los pedidos (@GetMapping), obtener un pedido por ID, etc.
    // Example where it might appear
@GetMapping("/someEndpoint")
public ResponseEntity<String> getSomething(
    @RequestParam(required = false) MultiValueMap<String, String> params) {
    if (params == null) {
        params = new org.springframework.util.LinkedMultiValueMap<>();
    }
    // ...
    return ResponseEntity.ok("Success");
}
    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error al obtener los pedidos: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.obtenerPedidoPorId(id);
            if (pedido != null) {
                return new ResponseEntity<>(pedido, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener el pedido por ID: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}