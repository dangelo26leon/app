package com.project.app.services;

import java.util.List;

import com.project.app.dto.ProductoRequest;
import com.project.app.models.Producto;

public interface ProductoService {

    Producto save(ProductoRequest productoResquest);
    List<Producto> findAll();
    Producto findById(Long id);
    Producto update(Long id, ProductoRequest productoRequest);
    void delete(Long id);

}
