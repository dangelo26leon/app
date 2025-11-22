# Checklist de Verificación del Backend

## Antes de Comenzar con el Frontend ✅

Este checklist te ayudará a verificar que todo está funcionando correctamente en tu backend.

---

## 1. Verificaciones Iniciales

### Base de Datos
- [ ] MySQL está instalado y corriendo
- [ ] Base de datos `appweb` creada
- [ ] Credenciales en `application.properties` son correctas

### Compilación
```bash
mvn clean compile
```
- [ ] La compilación fue exitosa sin errores
- [ ] Solo hay advertencias (deprecation), no errores

---

## 2. Iniciar el Servidor

```bash
mvn spring-boot:run
```

O usando el JAR compilado:
```bash
java -jar target/app-0.0.1-SNAPSHOT.jar
```

- [ ] El servidor inició sin errores
- [ ] Ves el mensaje: "Tomcat started on port(s): 8080"
- [ ] No hay mensajes de error en la consola

---

## 3. Verificar Swagger UI

Abre en tu navegador:
```
http://localhost:8080/swagger-ui.html
```

- [ ] Swagger UI se cargó correctamente
- [ ] Puedes ver todos los endpoints listados
- [ ] Los endpoints están agrupados por controlador (auth, usuarios, productos, categorías)

---

## 4. Pruebas de Endpoints

### 4.1 Registro de Usuario

En Swagger UI, busca `POST /api/users/register`:

```json
{
  "username": "testuser",
  "password": "Test@12345",
  "name": "Test User",
  "email": "testuser@example.com"
}
```

- [ ] Retorna código 201 (Created)
- [ ] El usuario se creó en la base de datos
- [ ] El rol USER se asignó automáticamente

### 4.2 Login

Busca `POST /auth/login`:

```json
{
  "username": "testuser",
  "password": "Test@12345"
}
```

- [ ] Retorna código 200 (OK)
- [ ] La respuesta incluye:
  - [ ] `token` (JWT)
  - [ ] `username`
  - [ ] `message` ("Login exitoso")
- [ ] Guarda el token para las siguientes pruebas

### 4.3 Obtener Perfil (Autenticado)

Busca `GET /api/users/profile`:

- [ ] Haz clic en el botón "Authorize" en Swagger
- [ ] Pega tu token: `Bearer <tu_token>`
- [ ] Ejecuta el endpoint
- [ ] Retorna código 200 con tu perfil

- [ ] Recibes los datos del usuario logueado
- [ ] La contraseña NO aparece en la respuesta

### 4.4 Crear Categoría (ADMIN)

Para crear una categoría necesitas ser ADMIN. Primero:

1. Crea un usuario con rol ADMIN en la base de datos:
```sql
-- Supongamos que el usuario testadmin tiene id=1
UPDATE users SET id = 1 WHERE username = 'testadmin';
-- Obtén el rol ADMIN id
SELECT id FROM Roles WHERE nombre_rol = 'ADMIN';
-- Crea la relación user_rol
INSERT INTO user_rol (user_id, rol_id) VALUES (1, <ADMIN_ID>);
```

O simplemente registra dos usuarios y usa el primero como ADMIN en la BD.

Busca `POST /api/categorias`:

```json
{
  "nombre": "Platos Principales",
  "descripcion": "Comidas principales del restaurante"
}
```

- [ ] Retorna código 201 (Created)
- [ ] La categoría se creó exitosamente
- [ ] Puedes ver el ID asignado

### 4.5 Listar Categorías

Busca `GET /api/categorias`:

- [ ] Retorna código 200
- [ ] Ves la categoría que acabas de crear
- [ ] La respuesta incluye el formato `ApiResponse` con `success`, `message` y `data`

### 4.6 Crear Producto (ADMIN)

Busca `POST /api/productos`:

```json
{
  "nombre": "Pollo a la Parrilla",
  "descripcion": "Pollo fresco cocinado a la parrilla",
  "precio": 25.99,
  "categoriaId": 1,
  "disponible": true
}
```

- [ ] Retorna código 201 (Created)
- [ ] El producto se creó exitosamente
- [ ] La categoría se asoció correctamente

### 4.7 Listar Productos

Busca `GET /api/productos`:

- [ ] Retorna código 200
- [ ] Ves el producto que creaste
- [ ] El formato de respuesta es `ApiResponse`

---

## 5. Pruebas de Validación de Roles

### 5.1 Crear Producto sin ser ADMIN

Como usuario no ADMIN, intenta `POST /api/productos`:

- [ ] Retorna código 403 (Forbidden)
- [ ] Mensaje: "Access Denied" o similar

### 5.2 Actualizar Tu Propio Perfil

Como usuario autenticado, busca `PUT /api/users/{id}`:

```json
{
  "username": "testuser",
  "password": "NewPassword@12345",
  "name": "Test User Updated",
  "email": "newemail@example.com"
}
```

- [ ] Retorna código 200
- [ ] Tu perfil fue actualizado
- [ ] Mensaje: "Usuario actualizado correctamente"

### 5.3 Intentar Actualizar Otro Usuario

Como usuario no ADMIN, intenta actualizar a otro usuario con ID diferente:

- [ ] Retorna código 403 (Forbidden)
- [ ] Mensaje: "No tienes permiso para actualizar este usuario"

---

## 6. Pruebas de Excepciones

### 6.1 Login con Credenciales Incorrectas

`POST /auth/login`:

```json
{
  "username": "testuser",
  "password": "PasswordIncorrecto"
}
```

- [ ] Retorna código 401 (Unauthorized)
- [ ] Mensaje: "Usuario o contraseña incorrectos"

### 6.2 Obtener Producto que No Existe

`GET /api/productos/99999`:

- [ ] Retorna código 404 (Not Found)
- [ ] Mensaje: "Producto no encontrado con id: 99999"

### 6.3 Token Expirado

1. Espera un poco o cambia el JWT Secret en `application.properties`
2. Intenta hacer una solicitud con el token antiguo

- [ ] Retorna código 403 (Forbidden)
- [ ] Mensaje: "El token ha expirado, por favor inicia sesión de nuevo."

---

## 7. Pruebas de Respuestas

### 7.1 Verificar Formato ApiResponse

Para cualquier endpoint GET exitoso, verifica la estructura:

```json
{
  "success": true,
  "message": "Descripción de la acción",
  "data": {
    // Los datos aquí
  }
}
```

- [ ] Todos los endpoints retornan este formato
- [ ] `success` es un booleano
- [ ] `message` es descriptivo
- [ ] `data` contiene los datos o es null

---

## 8. Verificar Base de Datos

Conecta a MySQL y verifica:

```sql
SELECT * FROM users;
SELECT * FROM Roles;
SELECT * FROM user_rol;
SELECT * FROM Categoria;
SELECT * FROM Producto;
```

- [ ] Se creó la tabla `users` con tus usuarios
- [ ] Se crearon los roles ADMIN y USER en tabla `Roles`
- [ ] Las relaciones en `user_rol` son correctas
- [ ] Las categorías se crearon correctamente
- [ ] Los productos con categoría se crearon correctamente

---

## 9. Pruebas de CORS

Desde una página en `http://localhost:3000` o `http://localhost:5173` (si usas Vite):

```javascript
fetch('http://localhost:8080/api/productos', {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json'
  }
})
.then(response => response.json())
.then(data => console.log(data));
```

- [ ] La solicitud se completa sin errores CORS
- [ ] Recibes los datos correctamente

---

## 10. Verificación Final

- [ ] Compilación exitosa: ✅ BUILD SUCCESS
- [ ] Servidor inicia sin errores: ✅ "Tomcat started on port(s): 8080"
- [ ] Swagger UI accesible
- [ ] Todos los endpoints funcionan
- [ ] Respuestas en formato correcto
- [ ] Autenticación y autorización funcionan
- [ ] Manejo de excepciones es correcto
- [ ] CORS está configurado

---

## Si Algo No Funciona

### Problema: "Base de datos no encontrada"
```
java.sql.SQLSyntaxErrorException: Unknown database 'appweb'
```

**Solución:**
```sql
CREATE DATABASE appweb;
```

---

### Problema: "Connection refused"
```
java.sql.SQLNonTransientConnectionException: Could not get a connection
```

**Solución:**
- Verifica que MySQL está corriendo
- Verifica las credenciales en `application.properties`

---

### Problema: "Token inválido"
**Solución:**
- Verifica que estás usando el token del último login
- Verifica que no ha expirado (24 horas)
- Verifica que está en formato: `Bearer <token>`

---

### Problema: "CORS policy"
```
Access to XMLHttpRequest has been blocked by CORS policy
```

**Solución:**
- Verifica que tu URL está en `allowedOrigins` en `WebSecurityConfig.java`
- Reinicia el servidor después de cambiar CORS

---

### Problema: "Swagger no carga"
**Solución:**
- Verifica que la dependencia `springdoc-openapi-starter-webmvc-ui` está en `pom.xml`
- Verifica que compilaste con `mvn clean install`

---

## Información de Soporte

**Servidor:** http://localhost:8080
**Swagger UI:** http://localhost:8080/swagger-ui.html
**OpenAPI JSON:** http://localhost:8080/v3/api-docs

**Archivos de configuración:**
- `application.properties` - Configuración principal
- `WebSecurityConfig.java` - Configuración de seguridad
- `OpenApiConfig.java` - Configuración de Swagger

---

## ✅ Listo para Frontend

Una vez que todos los checks están marcados, tu backend está **100% funcional** y listo para conectar con el frontend.

Procede con confianza a desarrollar tu aplicación frontend. 🚀
