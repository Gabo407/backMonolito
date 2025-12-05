package com.datafixcomp.monolito.config;

import com.datafixcomp.monolito.producto.entity.Producto;
import com.datafixcomp.monolito.producto.repository.ProductoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer {

    @Autowired
    private ProductoRepository productoRepository;

    @PostConstruct
    public void init() {
        if (productoRepository.count() == 0) {
            System.out.println("Inicializando base de datos con productos de ejemplo...");

            // Productos RAM
            productoRepository.save(createProducto(
                    "Kingston Fury Beast 8GB DDR4 3200MHz",
                    "RAM",
                    new BigDecimal("45.99"),
                    "Memoria RAM DDR4 de alto rendimiento",
                    15));

            productoRepository.save(createProducto(
                    "Corsair Vengeance 16GB DDR4 3600MHz",
                    "RAM",
                    new BigDecimal("89.99"),
                    "Kit de memoria RAM gaming de alta velocidad",
                    20));

            productoRepository.save(createProducto(
                    "G.Skill Trident Z 32GB DDR4 3200MHz",
                    "RAM",
                    new BigDecimal("149.99"),
                    "Memoria RAM premium con RGB",
                    10));

            // Productos SSD
            productoRepository.save(createProducto(
                    "Samsung 970 EVO Plus 500GB NVMe",
                    "SSD",
                    new BigDecimal("79.99"),
                    "SSD NVMe M.2 de alta velocidad",
                    25));

            productoRepository.save(createProducto(
                    "WD Black SN850 1TB NVMe",
                    "SSD",
                    new BigDecimal("129.99"),
                    "SSD NVMe Gen4 para gaming",
                    18));

            productoRepository.save(createProducto(
                    "Crucial P5 Plus 2TB NVMe",
                    "SSD",
                    new BigDecimal("199.99"),
                    "SSD NVMe de gran capacidad",
                    12));

            // Productos GPU
            productoRepository.save(createProducto(
                    "NVIDIA GeForce RTX 3060 12GB",
                    "GPU",
                    new BigDecimal("399.99"),
                    "Tarjeta gráfica para gaming 1080p/1440p",
                    8));

            productoRepository.save(createProducto(
                    "AMD Radeon RX 6700 XT 12GB",
                    "GPU",
                    new BigDecimal("449.99"),
                    "GPU de alto rendimiento para gaming",
                    6));

            productoRepository.save(createProducto(
                    "NVIDIA GeForce RTX 4070 12GB",
                    "GPU",
                    new BigDecimal("599.99"),
                    "Tarjeta gráfica de última generación",
                    5));

            System.out.println("Base de datos inicializada con " + productoRepository.count() + " productos.");
        }
    }

    private Producto createProducto(String nombre, String categoria, BigDecimal precio, String descripcion, int stock) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setStock(stock);
        return producto;
    }
}
