package com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.app.models.Producto;

public interface ProductoRepository extends JpaRepository <Producto, Long> {

}
