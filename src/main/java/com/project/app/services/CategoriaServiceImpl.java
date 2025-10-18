package com.project.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.app.models.Categoria;
import com.project.app.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
    
    @Override
    public Categoria findById(Long id) {
        // Lanza una excepción RuntimeException si la categoría no es encontrada. 
        // En un proyecto más grande, usarías una excepción personalizada (ej. ResourceNotFoundException).
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }
    
    @Override
    public Categoria update(Long id, Categoria categoriaDetalles) {
        // 1. Verificar si la categoría existe
        Categoria categoriaExistente = findById(id); 

        // 2. Actualizar los campos
        categoriaExistente.setNombre(categoriaDetalles.getNombre());
        categoriaExistente.setDescripcion(categoriaDetalles.getDescripcion());
        
        // 3. Guardar y devolver la categoría actualizada
        return categoriaRepository.save(categoriaExistente);
    }
    
    @Override
    public void delete(Long id) {
        // 1. Verificar si la categoría existe (findById lo hace y lanza la excepción si no está)
        Categoria categoria = findById(id);
        
        // 2. Eliminar la categoría
        categoriaRepository.delete(categoria);
    }

}
