package com.datafixcomp.monolito.producto.repository;

import com.datafixcomp.monolito.producto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    List<Producto> findByCategoria(String categoria);
    
    List<Producto> findByStockGreaterThan(Integer stock);
    
    List<Producto> findByCategoriaOrderByPrecioAsc(String categoria);
}
