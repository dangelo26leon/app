# Guía de Integración Frontend - Backend

## Backend Status ✅

Tu backend está **completamente terminado y funcional**. Aquí está lo que se ha completado:

### ✅ Implementado

1. **Autenticación JWT**
   - Login con generación de token
   - Validación de token en cada request
   - Expiración configurable (24 horas)

2. **Gestión de Usuarios**
   - Registro de nuevos usuarios
   - Login
   - Obtener perfil del usuario autenticado
   - Actualizar perfil personal
   - Listar usuarios (solo ADMIN)
   - Eliminar usuarios (solo ADMIN)
   - Encriptación de contraseñas con BCrypt

3. **Gestión de Productos**
   - Crear producto (solo ADMIN)
   - Listar todos los productos (público)
   - Obtener producto por ID (público)
   - Actualizar producto (solo ADMIN)
   - Eliminar producto (solo ADMIN)
   - Relación con categorías

4. **Gestión de Categorías**
   - Crear categoría (solo ADMIN)
   - Listar todas las categorías (público)
   - Obtener categoría por ID (público)
   - Actualizar categoría (solo ADMIN)
   - Eliminar categoría (solo ADMIN)

5. **Seguridad y Control de Acceso**
   - Roles (ADMIN, USER)
   - Control de acceso basado en roles (@PreAuthorize)
   - CORS habilitado
   - Validación de autorización en endpoints sensibles

6. **Manejo de Excepciones**
   - Respuestas consistentes en formato JSON
   - Manejo centralizado de errores
   - Mensajes de error descriptivos
   - Códigos HTTP apropiados

7. **Documentación**
   - Swagger UI en /swagger-ui.html
   - OpenAPI documentation
   - README con ejemplos de uso

## Configuración para Conexión Frontend

### URL Base del Backend
```
http://localhost:8080
```

### Headers Requeridos
Para todas las solicitudes autenticadas:
```
Authorization: Bearer <tu_token_jwt>
Content-Type: application/json
```

## Flujo de Autenticación Recomendado

### 1. Registro (opcional)
```javascript
POST /api/users/register
Content-Type: application/json

{
  "username": "usuario1",
  "password": "password123",
  "name": "Juan Pérez",
  "email": "juan@example.com"
}

Respuesta:
{
  "id": 1,
  "username": "usuario1",
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "roles": [
    {
      "id": 1,
      "rol": "USER",
      "descripcion": "Usuario estándar del sistema"
    }
  ]
}
```

### 2. Login
```javascript
POST /auth/login
Content-Type: application/json

{
  "username": "usuario1",
  "password": "password123"
}

Respuesta:
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyaW8xIiwiY2xhc3NzcGF0aHMiOlsiY29tLnByb2plY3QuYXBwLm1vZGVscy5Sb2wiXSwiY2xhc3Nwb3RoIjoiY29tLnByb2plY3QuYXBwLm1vZGVscy5Sb2wiLCJjbGFzc25hbWUiOiJqYXZhLnV0aWwuSGFzaFNldCIsImlhdCI6MTczMjI2NTk1MywiZXhwIjoxNzMyMzUyMzUzfQ.L4c3QaJ5nH8K9vB2mN3pQ6wX1yZ0sA9cD6eF7gH0...",
  "username": "usuario1",
  "message": "Login exitoso"
}
```

### 3. Guardar Token
Almacena el token en:
- **localStorage** (más simple)
- **sessionStorage** (más seguro)
- **httpOnly cookies** (más recomendado)

**Ejemplo en JavaScript:**
```javascript
localStorage.setItem('token', response.token);
```

### 4. Usar Token en Solicitudes
Agrega el token en cada solicitud autenticada:

**JavaScript con Fetch:**
```javascript
const token = localStorage.getItem('token');

fetch('http://localhost:8080/api/usuarios/profile', {
  method: 'GET',
  headers: {
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  }
})
.then(response => response.json())
.then(data => console.log(data));
```

**JavaScript con Axios:**
```javascript
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Content-Type': 'application/json'
  }
});

// Agregar interceptor para token
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;
```

## Endpoints para el Frontend

### Autenticación
```
POST   /auth/login              - Login
POST   /api/users/register      - Registro de usuario
```

### Usuarios
```
GET    /api/users/profile       - Obtener mi perfil (requiere autenticación)
GET    /api/users/all           - Listar usuarios (solo ADMIN)
GET    /api/users/{id}          - Obtener usuario por ID (solo ADMIN)
PUT    /api/users/{id}          - Actualizar usuario
DELETE /api/users/{id}          - Eliminar usuario (solo ADMIN)
```

### Productos
```
GET    /api/productos           - Listar productos
GET    /api/productos/{id}      - Obtener producto por ID
POST   /api/productos           - Crear producto (solo ADMIN)
PUT    /api/productos/{id}      - Actualizar producto (solo ADMIN)
DELETE /api/productos/{id}      - Eliminar producto (solo ADMIN)
```

### Categorías
```
GET    /api/categorias          - Listar categorías
GET    /api/categorias/{id}     - Obtener categoría por ID
POST   /api/categorias          - Crear categoría (solo ADMIN)
PUT    /api/categorias/{id}     - Actualizar categoría (solo ADMIN)
DELETE /api/categorias/{id}     - Eliminar categoría (solo ADMIN)
```

## Estructura de Respuestas

### Respuesta Exitosa
```json
{
  "success": true,
  "message": "Descripción de la acción",
  "data": {
    // Objeto o array de datos
  }
}
```

### Respuesta de Error
```json
{
  "success": false,
  "message": "Descripción del error",
  "data": null
}
```

### Respuesta de Login
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "usuario1",
  "message": "Login exitoso"
}
```

## Códigos HTTP Utilizados

- **200 OK** - Solicitud exitosa
- **201 Created** - Recurso creado exitosamente
- **204 No Content** - Recurso eliminado exitosamente
- **400 Bad Request** - Solicitud inválida
- **401 Unauthorized** - Autenticación requerida
- **403 Forbidden** - Acceso denegado / Token expirado
- **404 Not Found** - Recurso no encontrado
- **500 Internal Server Error** - Error del servidor

## Ejecución del Backend

### Opción 1: Con Maven
```bash
mvn spring-boot:run
```

### Opción 2: Con JAR compilado
```bash
java -jar target/app-0.0.1-SNAPSHOT.jar
```

## Verificación rápida

Abre en tu navegador:
```
http://localhost:8080/swagger-ui.html
```

Aquí puedes probar todos los endpoints sin escribir código.

## Configuración para Producción

Antes de desplegar a producción, actualiza:

1. **JWT Secret** en `application.properties`:
   ```properties
   jwt.secret=TU_CLAVE_SUPER_SECRETA_MUY_LARGA_Y_SEGURA
   ```

2. **CORS Origins** en `WebSecurityConfig.java`:
   ```java
   configuration.setAllowedOrigins(Arrays.asList("https://tudominio.com"));
   ```

3. **Base de datos** - Usa una instancia MySQL en producción

4. **Port** - Cambia en `application.properties`:
   ```properties
   server.port=8080
   ```

## Troubleshooting

### Error: "Base de datos no encontrada"
- Verifica que MySQL está corriendo
- Crea la base de datos: `CREATE DATABASE appweb;`
- Verifica las credenciales en `application.properties`

### Error: "Token inválido"
- Verifica que el token está siendo enviado correctamente
- Asegúrate de incluir "Bearer " antes del token
- El token puede haber expirado (24 horas)

### Error: "CORS policy"
- Verifica que tu frontend URL está en la lista permitida en `WebSecurityConfig.java`

## Próximos Pasos

1. ✅ Backend completado
2. 📱 **Ahora puedes comenzar con el frontend**
3. 🔗 Conecta tu frontend con los endpoints documentados
4. 🧪 Prueba en Swagger UI: http://localhost:8080/swagger-ui.html
5. 📦 Prepara para producción

¡Tu backend está listo para producción! 🚀
