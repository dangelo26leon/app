package com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.app.models.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Long> {

}
