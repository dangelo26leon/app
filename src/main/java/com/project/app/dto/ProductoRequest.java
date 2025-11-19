package com.project.app.dto;

import lombok.Data;

@Data
public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private double precio;
    private Long categoriaId;
    private boolean disponible = true;
}
