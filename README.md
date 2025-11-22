# Backend Restaurante - Documentación

## Descripción
Backend API para un sistema de gestión de restaurante construido con Spring Boot 3.5.7, Java 21 y MySQL.

## Características Principales
- ✅ Autenticación y autorización con JWT
- ✅ Gestión de usuarios (registro, login, perfil)
- ✅ Gestión de productos con categorías
- ✅ Control de acceso basado en roles (ADMIN, USER)
- ✅ Manejo centralizado de excepciones
- ✅ Documentación OpenAPI/Swagger
- ✅ CORS habilitado

## Requisitos
- Java 21 o superior
- MySQL 5.7+
- Maven 3.6+

## Configuración de Base de Datos

1. **Crear la base de datos:**
```sql
CREATE DATABASE appweb;
```

2. **Actualizar credenciales en `src/main/resources/application.properties`:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/appweb
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

## Instalación y Ejecución

### 1. Descargar dependencias
```bash
mvn clean install
```

### 2. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

O compilar y ejecutar el JAR:
```bash
mvn clean package
java -jar target/app-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en: **http://localhost:8080**

## Documentación de la API

Una vez que el servidor está corriendo, accede a:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## Endpoints Principales

### Autenticación
- `POST /auth/login` - Login y obtener token JWT
- `POST /api/users/register` - Registrar nuevo usuario

### Usuarios
- `GET /api/users/profile` - Obtener perfil del usuario autenticado
- `GET /api/users/all` - Listar todos los usuarios (requiere rol ADMIN)
- `GET /api/users/{id}` - Obtener usuario por ID (requiere rol ADMIN)
- `PUT /api/users/{id}` - Actualizar usuario
- `DELETE /api/users/{id}` - Eliminar usuario (requiere rol ADMIN)

### Productos
- `GET /api/productos` - Listar todos los productos
- `GET /api/productos/{id}` - Obtener producto por ID
- `POST /api/productos` - Crear producto (requiere rol ADMIN)
- `PUT /api/productos/{id}` - Actualizar producto (requiere rol ADMIN)
- `DELETE /api/productos/{id}` - Eliminar producto (requiere rol ADMIN)

### Categorías
- `GET /api/categorias` - Listar todas las categorías
- `GET /api/categorias/{id}` - Obtener categoría por ID
- `POST /api/categorias` - Crear categoría (requiere rol ADMIN)
- `PUT /api/categorias/{id}` - Actualizar categoría (requiere rol ADMIN)
- `DELETE /api/categorias/{id}` - Eliminar categoría (requiere rol ADMIN)

## Estructura del Proyecto

```
src/main/java/com/project/app/
├── AppApplication.java           # Clase principal de Spring Boot
├── config/                        # Configuraciones
│   ├── JwtAuthenticationFilter.java
│   ├── JwtTokenService.java
│   ├── WebSecurityConfig.java
│   └── OpenApiConfig.java
├── controllers/                   # Controladores REST
│   ├── AuthController.java
│   ├── UserController.java
│   ├── ProductoController.java
│   └── CategoriaController.java
├── dto/                           # Data Transfer Objects
│   ├── LoginDto.java
│   ├── ProductoRequest.java
│   ├── ApiResponse.java
│   └── AuthResponse.java
├── models/                        # Entidades JPA
│   ├── User.java
│   ├── Producto.java
│   ├── Categoria.java
│   ├── Rol.java
│   └── TipoRol.java
├── repository/                    # Repositorios JPA
│   ├── UserRepository.java
│   ├── ProductoRepository.java
│   ├── CategoriaRepository.java
│   └── RolRepository.java
├── services/                      # Servicios
│   ├── UserService.java
│   ├── UserServiceImpl.java
│   ├── ProductoService.java
│   ├── ProductoServiceImpl.java
│   ├── CategoriaService.java
│   ├── CategoriaServiceImpl.java
│   └── UserDetailsServiceImpl.java
└── exceptions/                    # Manejo de excepciones
    └── GlobalExceptionHandler.java
```

## Ejemplo de Uso

### 1. Registrar un usuario
```bash
curl -X POST http://localhost:8080/api/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "usuario1",
    "password": "password123",
    "name": "Juan Pérez",
    "email": "juan@example.com"
  }'
```

### 2. Login
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "usuario1",
    "password": "password123"
  }'
```

Respuesta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "usuario1",
  "message": "Login exitoso"
}
```

### 3. Usar el token en solicitudes autenticadas
```bash
curl -X GET http://localhost:8080/api/users/profile \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

## Configuración JWT

La configuración JWT se encuentra en `src/main/resources/application.properties`:

```properties
jwt.secret=9a2f8c4e6b7a1d5f9a2f8c4e6b7a1d5f9a2f8c4e6b7a1d5f9a2f8c4e6b7a1d5f
jwt.expiration=86400000  # 24 horas en milisegundos
```

⚠️ **IMPORTANTE**: En producción, cambia la clave secreta por una más segura.

## Roles y Permisos

### ROLE_USER
- Perfil personal
- Registrarse
- Actualizar su propio perfil
- Ver productos y categorías

### ROLE_ADMIN
- Todas las operaciones de usuarios
- Crear, editar, eliminar productos
- Crear, editar, eliminar categorías
- Acceso total

## CORS

La API está configurada para aceptar solicitudes desde:
- http://localhost:3000
- http://localhost:4200
- *

Ajusta estas configuraciones en `WebSecurityConfig.java` según sea necesario.

## Tecnologías Utilizadas

- **Spring Boot**: 3.5.7
- **Java**: 21
- **MySQL**: Base de datos
- **JWT**: Autenticación segura
- **Lombok**: Reducción de boilerplate
- **Spring Security**: Control de acceso
- **Springdoc OpenAPI**: Documentación automática
- **Maven**: Gestor de dependencias

## Status y Estado del Proyecto

✅ **Backend Terminado y Funcional**

Características completadas:
- ✅ Autenticación JWT
- ✅ Gestión de usuarios
- ✅ Gestión de productos y categorías
- ✅ Manejo de excepciones mejorado
- ✅ Respuestas API consistentes
- ✅ Documentación Swagger/OpenAPI
- ✅ Control de acceso basado en roles
- ✅ CORS configurado

Listo para conectar con el frontend.

## Notas Adicionales

- La aplicación crea automáticamente los roles (ADMIN, USER) en el primer inicio
- Las contraseñas se almacenan encriptadas con BCrypt
- Todos los datos se validan en el servidor
- Los tokens JWT expiran después de 24 horas

## Contacto y Soporte

Para reportar problemas o sugerencias, contacta al equipo de desarrollo.
