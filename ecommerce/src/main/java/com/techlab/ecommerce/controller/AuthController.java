package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.dto.LoginRequest;
import com.techlab.ecommerce.dto.LoginResponse;
import com.techlab.ecommerce.model.Cliente;
import com.techlab.ecommerce.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final ClienteService clienteService;

    public AuthController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        Optional<Cliente> userOpt = clienteService.findByEmail(req.getEmail());
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(req.getPassword())) {
            // Para demo: si el email es admin@admin.com, es admin
            String role = req.getEmail().equals("admin@admin.com") ? "admin" : "user";
            String token = UUID.randomUUID().toString(); // Solo ejemplo, no seguro
            return ResponseEntity.ok(new LoginResponse(token, role, userOpt.get().getId()));
        }
        return ResponseEntity.status(401).build();
    }
}