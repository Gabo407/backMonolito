package com.datafixcomp.monolito.producto.controller;

import com.datafixcomp.monolito.producto.entity.Producto;
import com.datafixcomp.monolito.producto.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "API para gestión de productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Listar todos los productos")
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Obtener productos por categoría")
    public ResponseEntity<List<Producto>> obtenerPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(productoService.buscarPorCategoria(categoria));
    }

    @GetMapping("/categoria/{categoria}/ordenado")
    @Operation(summary = "Obtener productos por categoría ordenados por precio")
    public ResponseEntity<List<Producto>> obtenerPorCategoriaOrdenado(@PathVariable String categoria) {
        return ResponseEntity.ok(productoService.buscarPorCategoriaOrdenado(categoria));
    }

    @GetMapping("/test")
    @Operation(summary = "Prueba de conexión con el backend")
    public ResponseEntity<java.util.Map<String, String>> testConnection() {
        return ResponseEntity.ok(java.util.Map.of(
                "status", "ok",
                "mensaje", "Backend conectado correctamente"));
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Obtener productos con stock disponible")
    public ResponseEntity<List<Producto>> obtenerDisponibles() {
        return ResponseEntity.ok(productoService.buscarConStock());
    }

    @PostMapping
    @Operation(summary = "Crear nuevo producto")
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto existente")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizar(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.ok("Producto eliminado exitosamente");
    }
}
