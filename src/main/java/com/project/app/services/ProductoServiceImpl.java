package com.project.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.app.models.Producto;
import com.project.app.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaService categoriaService; 

    @Override
    public Producto save(Producto producto) {
        // En una app real, deberías validar que producto.getCategoria() no sea null
        // y que el ID de la categoría exista en la base de datos antes de guardar.
        
        // Simplemente guardamos asumiendo que el objeto Producto tiene una Categoria válida.
        return productoRepository.save(producto);
    }
    
    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    
    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }
    
    @Override
    public Producto update(Long id, Producto productoDetalles) {
        Producto productoExistente = findById(id); // Reutilizamos findById para validar existencia

        // Actualiza los campos principales
        productoExistente.setNombre(productoDetalles.getNombre());
        productoExistente.setDescripcion(productoDetalles.getDescripcion());
        productoExistente.setPrecio(productoDetalles.getPrecio());
        productoExistente.setDisponible(productoDetalles.isDisponible());
        
        // Si la categoría ha cambiado, se actualiza:
        if (productoDetalles.getCategoria() != null && productoDetalles.getCategoria().getId() != null) {
             // Es una buena práctica buscar la categoría para asegurar que el ID es válido
            productoExistente.setCategoria(categoriaService.findById(productoDetalles.getCategoria().getId()));
        }

        return productoRepository.save(productoExistente);
    }
    
    @Override
    public void delete(Long id) {
        Producto producto = findById(id);
        productoRepository.delete(producto);
    }

}
