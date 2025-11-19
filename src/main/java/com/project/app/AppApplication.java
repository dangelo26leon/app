package com.project.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.app.models.Rol;
import com.project.app.models.TipoRol;
import com.project.app.repository.RolRepository;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
    public CommandLineRunner initData(RolRepository rolRepository) {
        return args -> {
            // Verificar si los roles ya existen
            if (rolRepository.findByRol(TipoRol.USER).isEmpty()) {
                Rol user = new Rol();
                user.setRol(TipoRol.USER);
                user.setDescripcion("Usuario estándar del sistema");
                rolRepository.save(user);
            }

            if (rolRepository.findByRol(TipoRol.ADMIN).isEmpty()) {
                Rol admin = new Rol();
                admin.setRol(TipoRol.ADMIN);
                admin.setDescripcion("Administrador del sistema, con acceso completo");
                rolRepository.save(admin);
            }
        };
    }

}
