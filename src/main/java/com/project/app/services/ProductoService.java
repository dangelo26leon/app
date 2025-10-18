package com.project.app.services;

import java.util.List;

import com.project.app.models.Producto;

public interface ProductoService {

    Producto save(Producto producto);
    List<Producto> findAll();
    Producto findById(Long id);
    Producto update(Long id, Producto productoDetalles);
    void delete(Long id);

}
