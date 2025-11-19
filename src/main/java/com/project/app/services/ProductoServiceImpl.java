package com.project.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.app.dto.ProductoRequest;
import com.project.app.models.Categoria;
import com.project.app.models.Producto;
import com.project.app.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaService categoriaService; 

    @Override
    public Producto save(ProductoRequest productoRequest) {

        Categoria categoria = categoriaService.findById(productoRequest.getCategoriaId());

        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(productoRequest.getNombre());
        nuevoProducto.setDescripcion(productoRequest.getDescripcion());
        nuevoProducto.setPrecio(productoRequest.getPrecio());
        nuevoProducto.setDisponible(productoRequest.isDisponible());
        nuevoProducto.setCategoria(categoria); 
        
        return productoRepository.save(nuevoProducto);
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
    public Producto update(Long id, ProductoRequest productoRequest) {
        Producto productoExistente = findById(id); 

        Categoria categoria = categoriaService.findById(productoRequest.getCategoriaId());

        // 2. Actualizar los campos
        productoExistente.setNombre(productoRequest.getNombre());
        productoExistente.setDescripcion(productoRequest.getDescripcion());
        productoExistente.setPrecio(productoRequest.getPrecio());
        productoExistente.setDisponible(productoRequest.isDisponible());
        productoExistente.setCategoria(categoria); 
        
        return productoRepository.save(productoExistente);
    }
    
    @Override
    public void delete(Long id) {
        Producto producto = findById(id);
        productoRepository.delete(producto);
    }

}
