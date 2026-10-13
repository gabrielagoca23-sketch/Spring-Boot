package com.ejemplo.demo.controller;

import com.ejemplo.demo.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200"})
public class ApiController {

    // =========================================================================
    // 👇 AQUÍ PUEDES MODIFICAR TUS DATOS MANUALES DE FORMA FÁCIL:
    // =========================================================================
    private final List<Usuario> listaUsuarios = new ArrayList<>(Arrays.asList(
        new Usuario(1L, "Gatita Gonzalez", "gatita@ejemplo.com"),
        new Usuario(2L, "Carlos Mendoza", "carlos.mendoza@ejemplo.com"),
        new Usuario(3L, "Laura Fernández", "laura.fernandez@ejemplo.com"),
        new Usuario(4L, "Mateo Silva", "mateo.silva@ejemplo.com")
    ));
    // =========================================================================

    @GetMapping("/datos")
    public ResponseEntity<Map<String, Object>> obtenerDatos() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "¡Hola! Conexión exitosa desde Spring Boot");
        respuesta.put("servidor", "Spring Boot 3 REST Controller");
        respuesta.put("estado", "OK");
        respuesta.put("codigo", 200);

        return ResponseEntity.ok(respuesta);
    }

    // Retorna el usuario según el ID recibido (ej: /api/usuarios/1)
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return listaUsuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Retorna todos los usuarios de la lista (ej: /api/usuarios)
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return ResponseEntity.ok(listaUsuarios);
    }
}
