package com.project.app.services;

import java.util.List;

import com.project.app.models.Categoria;

public interface CategoriaService {

    Categoria save(Categoria categoria);
    List<Categoria> findAll();
    Categoria findById(Long id);
    Categoria update(Long id, Categoria categoriaDetalles); 
    void delete(Long id);

}
