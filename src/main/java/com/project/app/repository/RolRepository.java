package com.project.app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.app.models.Rol;
import com.project.app.models.TipoRol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRol(TipoRol rol);

}
