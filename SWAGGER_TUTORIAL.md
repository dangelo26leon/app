# 📖 Guía Completa: Cómo Probar Endpoints en Swagger UI

## ¿Qué es Swagger UI?

Swagger UI es una **interfaz visual interactiva** donde puedes ver todos tus endpoints y probarlos directamente sin escribir código. Es como un "Postman" integrado en tu aplicación.

---

## Paso 1: Inicia tu Backend

Primero, asegúrate que tu backend está corriendo:

```bash
cd c:\Users\leonv\Documents\backend-restaurante
mvn spring-boot:run
```

Deberías ver en la consola:
```
Tomcat started on port(s): 8080 (http)
```

---

## Paso 2: Abre Swagger UI

En tu navegador (Chrome, Firefox, Edge, etc), abre:

```
http://localhost:8080/swagger-ui.html
```

Deberías ver una página que se parece a esto:

```
╔════════════════════════════════════════════════════════════════╗
║  API Restaurante                                    v1.0.0    ║
║  Comidas principales del restaurante                          ║
╠════════════════════════════════════════════════════════════════╣
║ Servers                                                       ║
║ ┌─────────────────────────────────────────────────────────┐ ║
║ │ http://localhost:8080                                  │ ║
║ └─────────────────────────────────────────────────────────┘ ║
╠════════════════════════════════════════════════════════════════╣
║                                                                ║
║ ▼ auth-controller                                             ║
║   POST /auth/login                                            ║
║   POST /api/users/register                                    ║
║                                                                ║
║ ▼ categoria-controller                                        ║
║   GET  /api/categorias                                        ║
║   POST /api/categorias                                        ║
║   GET  /api/categorias/{id}                                   ║
║   PUT  /api/categorias/{id}                                   ║
║   DELETE /api/categorias/{id}                                 ║
║                                                                ║
║ ▼ producto-controller                                         ║
║   GET  /api/productos                                         ║
║   POST /api/productos                                         ║
║   GET  /api/productos/{id}                                    ║
║   PUT  /api/productos/{id}                                    ║
║   DELETE /api/productos/{id}                                  ║
║                                                                ║
║ ▼ user-controller                                             ║
║   GET  /api/users/profile                                     ║
║   GET  /api/users/all                                         ║
║   GET  /api/users/{id}                                        ║
║   POST /api/users/register                                    ║
║   PUT  /api/users/{id}                                        ║
║   DELETE /api/users/{id}                                      ║
║                                                                ║
╚════════════════════════════════════════════════════════════════╝
```

Si no ves esto, verifica que:
1. El backend está corriendo (`mvn spring-boot:run`)
2. Escribiste bien la URL (`http://localhost:8080/swagger-ui.html`)
3. No hay errores en la consola

---

## Paso 3: Ejemplo Práctico - Probar GET /api/productos

### 3.1 Encuentra el endpoint

Busca en la página el texto **"GET /api/productos"** y haz clic en él.

Se expandirá mostrando algo como:

```
┌────────────────────────────────────────────────────────────┐
│ GET /api/productos                                         │
│ Listar productos                                           │
│                                                            │
│ [Try it out]  [Download]                                  │
│                                                            │
│ Parameters: (none)                                         │
│                                                            │
│ Responses:                                                 │
│ 200 - successful operation [application/json]             │
│ 401 - unauthorized [application/json]                     │
│ 403 - forbidden [application/json]                        │
│ 405 - invalid input [application/json]                    │
└────────────────────────────────────────────────────────────┘
```

### 3.2 Haz clic en "Try it out"

Verás un botón azul que dice **"Try it out"**. Haz clic en él.

La interfaz cambiará para permitirte escribir datos:

```
┌────────────────────────────────────────────────────────────┐
│ GET /api/productos                                         │
│                                                            │
│ [Cancel] [Execute]                                         │
│                                                            │
│ Parameters: (no parameters needed)                         │
│                                                            │
│ Request body:  (no body needed for GET)                    │
│                                                            │
│ Curl:                                                      │
│ curl -X GET "http://localhost:8080/api/productos"         │
│     -H "accept: application/json"                         │
└────────────────────────────────────────────────────────────┘
```

### 3.3 Haz clic en "Execute"

Haz clic en el botón azul **"Execute"** para enviar la solicitud.

### 3.4 Mira la respuesta

Scrollea hacia abajo un poco. Verás la respuesta:

```json
{
  "success": true,
  "message": "Productos obtenidos correctamente",
  "data": [
    {
      "id": 1,
      "nombre": "Pollo a la Parrilla",
      "descripcion": "Pollo fresco cocinado a la parrilla",
      "precio": 25.99,
      "categoria": {
        "id": 1,
        "nombre": "Platos Principales",
        "descripcion": "Comidas principales del restaurante"
      },
      "disponible": true
    }
  ]
}
```

¡Eso es! ✅ Ya hiciste tu primera prueba exitosa.

---

## Paso 4: Ejemplo con POST - Registrar un Usuario

### 4.1 Encuentra el endpoint POST

Busca **"POST /api/users/register"** y haz clic en él.

### 4.2 Haz clic en "Try it out"

Se abrirá un área donde puedes escribir datos.

### 4.3 Escribe los datos en el "Request body"

Verás un área donde dice **"Request body"** con un ejemplo. Reemplázalo con:

```json
{
  "username": "testuser1",
  "password": "Password123",
  "name": "Juan Pérez",
  "email": "juan@example.com"
}
```

Así se ve en Swagger:

```
┌────────────────────────────────────────────────────────────┐
│ POST /api/users/register                                   │
│                                                            │
│ [Cancel] [Execute]                                         │
│                                                            │
│ Request body: *                                            │
│ ┌──────────────────────────────────────────────────────┐  │
│ │ {                                                    │  │
│ │   "username": "testuser1",                           │  │
│ │   "password": "Password123",                         │  │
│ │   "name": "Juan Pérez",                              │  │
│ │   "email": "juan@example.com"                        │  │
│ │ }                                                    │  │
│ └──────────────────────────────────────────────────────┘  │
│                                                            │
│ Content-Type: application/json                            │
└────────────────────────────────────────────────────────────┘
```

### 4.4 Haz clic en "Execute"

### 4.5 Mira la respuesta

Deberías ver:

```json
{
  "id": 1,
  "username": "testuser1",
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

✅ ¡Usuario registrado exitosamente!

---

## Paso 5: Ejemplo con Autenticación - Login

### 5.1 Busca "POST /auth/login"

### 5.2 Haz clic en "Try it out"

### 5.3 Escribe los datos

Usa el usuario que acabas de crear:

```json
{
  "username": "testuser1",
  "password": "Password123"
}
```

### 5.4 Ejecuta

Haz clic en "Execute"

### 5.5 Mira la respuesta

Deberías ver algo como:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlcjEiLCJjbGFzc3NwYXRocyI6WyJjb20ucHJvamVjdC5hcHAubW9kZWxzLlJvbCJdLCJjbGFzc3BhdGgiOiJjb20ucHJvamVjdC5hcHAubW9kZWxzLlJvbCIsImNsYXNzbmFtZSI6ImphdmEudXRpbC5IYXNoU2V0IiwiaWF0IjoxNzMyMjczMDY1LCJleHAiOjE3MzIzNTk0NjV9.L4c3QaJ5nH8K9vB2mN3pQ6wX1yZ0sA9cD6eF7gH0...",
  "username": "testuser1",
  "message": "Login exitoso"
}
```

**⚠️ IMPORTANTE:** Copia el `token` (la cadena larga que empieza con `eyJ...`)

---

## Paso 6: Usar el Token en Endpoints Autenticados

Algunos endpoints requieren que envíes el token que recibiste del login.

### 6.1 Haz clic en el botón "Authorize" (arriba a la derecha)

En la parte superior derecha de Swagger UI, verás un botón que dice **"Authorize"**.

Haz clic en él:

```
┌──────────────────────────────────┐
│ Authorize                        │
│                                  │
│ Available authorizations:        │
│                                  │
│ Bearer Authentication            │
│ ┌──────────────────────────────┐│
│ │ (paste your JWT token here)  ││
│ └──────────────────────────────┘│
│                                  │
│ [Authorize]  [Close]             │
└──────────────────────────────────┘
```

### 6.2 Pega el token

En el campo que dice "(paste your JWT token here)" o similar, pega:

```
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlcjEi...
```

**IMPORTANTE:** Incluye la palabra `Bearer` seguida de un espacio, luego el token.

### 6.3 Haz clic en "Authorize"

Verás un mensaje de éxito. Haz clic en "Close".

Ahora Swagger UI va a incluir automáticamente el token en todas tus solicitudes siguientes.

---

## Paso 7: Probar un Endpoint Protegido

Ahora que autorizaste, puedes probar endpoints que requieren autenticación.

### Ejemplo: GET /api/users/profile

1. Busca "GET /api/users/profile"
2. Haz clic en "Try it out"
3. Haz clic en "Execute"

Deberías ver tu perfil:

```json
{
  "id": 1,
  "username": "testuser1",
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

✅ ¡Funcionó!

---

## Referencia Rápida: Colores en Swagger

```
🟢 GET     - Obtener datos (lectura)
🔵 POST    - Crear datos (escritura)
🟠 PUT     - Actualizar datos (actualización)
🔴 DELETE  - Eliminar datos (eliminación)
```

---

## Referencia Rápida: Códigos de Respuesta

```
200 ✅ OK               - La solicitud fue exitosa
201 ✅ Created          - El recurso fue creado exitosamente
204 ✅ No Content       - La operación fue exitosa (sin contenido en respuesta)
400 ❌ Bad Request      - Tu solicitud tiene errores
401 ❌ Unauthorized     - Necesitas un token válido
403 ❌ Forbidden        - No tienes permisos (acceso denegado)
404 ❌ Not Found        - El recurso no existe
500 ❌ Server Error     - Error en el servidor
```

---

## Flujo Completo: Crear un Producto

1. **Login**: POST /auth/login
   - Usa credenciales
   - Obtienes un token

2. **Autorizar**: Haz clic en "Authorize"
   - Pega el token

3. **Crear Categoría** (opcional): POST /api/categorias
   - Nombre: "Platos Principales"
   - Descripción: "Comidas principales"
   - Guarda el ID de la categoría

4. **Crear Producto**: POST /api/productos
   ```json
   {
     "nombre": "Bistec a la Parrilla",
     "descripcion": "Corte premium cocinado perfectamente",
     "precio": 45.99,
     "categoriaId": 1,
     "disponible": true
   }
   ```

5. **Verificar**: GET /api/productos
   - Verás tu nuevo producto listado

---

## Solución de Problemas

### Problema: "Token expirado"
```
{
  "success": false,
  "message": "El token ha expirado, por favor inicia sesión de nuevo."
}
```

**Solución:** Haz login nuevamente para obtener un nuevo token

---

### Problema: "No tienes permiso"
```
{
  "success": false,
  "message": "Access Denied"
}
```

**Solución:** 
- Necesitas ser ADMIN para esa operación
- O no estás autenticado (falta el token)

---

### Problema: "Categoría no encontrada"
```
{
  "success": false,
  "message": "Categoría no encontrada con ID: 999"
}
```

**Solución:** Usa un ID de categoría válido (que exista en la BD)

---

## Atajos Útiles en Swagger

| Acción | Cómo | Resultado |
|--------|------|-----------|
| Expandir todo | Click en "Expand all" | Ver todos los endpoints expandidos |
| Contraer todo | Click en "Collapse all" | Ocultar detalles |
| Buscar | Usa Ctrl+F en el navegador | Encontrar un endpoint específico |
| Copiar Curl | Click en "Copy" | Copiar comando para terminal |
| Desautorizar | Click en "Authorize" → "Logout" | Remover el token |

---

## Ejemplos Prácticos Listos para Copiar

### 1. Registrar Usuario
```json
{
  "username": "juan_perez",
  "password": "MiPassword@123",
  "name": "Juan Pérez García",
  "email": "juan@miempresa.com"
}
```

### 2. Login
```json
{
  "username": "juan_perez",
  "password": "MiPassword@123"
}
```

### 3. Crear Categoría
```json
{
  "nombre": "Bebidas",
  "descripcion": "Refrescos y bebidas frías"
}
```

### 4. Crear Producto
```json
{
  "nombre": "Coca Cola 350ml",
  "descripcion": "Refresco cola gelado",
  "precio": 3.50,
  "categoriaId": 1,
  "disponible": true
}
```

### 5. Actualizar Producto
```json
{
  "nombre": "Coca Cola 500ml",
  "descripcion": "Refresco cola gelado - formato grande",
  "precio": 4.50,
  "categoriaId": 1,
  "disponible": true
}
```

---

## ¡Ahora Eres un Experto en Swagger UI! 🎉

Ya sabes cómo:
✅ Abrir Swagger UI
✅ Probar endpoints GET, POST, PUT, DELETE
✅ Enviar datos JSON
✅ Usar autenticación con tokens
✅ Interpretar respuestas

Practica creando algunos usuarios, categorías y productos para familiarizarte. ¡Es muy fácil!
