package com.project.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.dto.ProductoRequest;
import com.project.app.dto.ApiResponse;
import com.project.app.models.Producto;
import com.project.app.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @GetMapping 
    public ResponseEntity<ApiResponse> obtenerTodosProductos() {
        try {
            List<Producto> productos = productoService.findAll();
            return ResponseEntity.ok(new ApiResponse(true, "Productos obtenidos correctamente", productos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/{id}") 
    public ResponseEntity<ApiResponse> obtenerProductoPorId(@PathVariable Long id) {
        try {
            Producto producto = productoService.findById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Producto obtenido correctamente", producto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(false, e.getMessage()));
        }
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> crearProducto(@RequestBody ProductoRequest productoRequest) {
        try {
            Producto nuevoProducto = productoService.save(productoRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Producto creado correctamente", nuevoProducto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}") 
    public ResponseEntity<ApiResponse> actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequest productoRequest) {
        try {
            Producto productoActualizado = productoService.update(id, productoRequest);
            return ResponseEntity.ok(new ApiResponse(true, "Producto actualizado correctamente", productoActualizado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(false, e.getMessage()));
        }
    }
    
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}") 
    public ResponseEntity<ApiResponse> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.delete(id);
            return ResponseEntity.ok(new ApiResponse(true, "Producto eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(false, e.getMessage()));
        }
    }

}
