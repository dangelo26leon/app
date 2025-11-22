# 🎬 TUTORIAL VISUAL: Probar Endpoints en Swagger UI - Paso a Paso

## 📌 Lo que Necesitas

1. ✅ Tu backend corriendo: `mvn spring-boot:run`
2. ✅ Un navegador web (Chrome, Firefox, Edge, etc)
3. ✅ 5 minutos de tu tiempo

---

## 🎯 OBJETIVO: Registrar un Usuario y Probar Endpoints

Vamos a hacer esto paso a paso visualmente.

---

## ⚡ PASO 1: Abre Swagger UI (1 minuto)

### En tu navegador, ve a:
```
http://localhost:8080/swagger-ui.html
```

### Deberías ver esto:

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃                                                              ┃
┃     API Restaurante  v1.0.0                                 ┃
┃                                                              ┃
┃     Authorize  🔒                                            ┃
┃                                                              ┃
┃  Servers: http://localhost:8080  ▼                          ┃
┃                                                              ┃
┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
┃                                                              ┃
┃  ▼ auth-controller                                           ┃
┃    🔵 POST /auth/login                                       ┃
┃    🔵 POST /api/users/register                               ┃
┃                                                              ┃
┃  ▼ categoria-controller                                      ┃
┃    🟢 GET /api/categorias                                    ┃
┃    🔵 POST /api/categorias                                   ┃
┃    🟢 GET /api/categorias/{id}                               ┃
┃    🟠 PUT /api/categorias/{id}                               ┃
┃    🔴 DELETE /api/categorias/{id}                            ┃
┃                                                              ┃
┃  ▼ producto-controller                                       ┃
┃    🟢 GET /api/productos                                     ┃
┃    🔵 POST /api/productos                                    ┃
┃    🟢 GET /api/productos/{id}                                ┃
┃    🟠 PUT /api/productos/{id}                                ┃
┃    🔴 DELETE /api/productos/{id}                             ┃
┃                                                              ┃
┃  ▼ user-controller                                           ┃
┃    🟢 GET /api/users/profile                                 ┃
┃    🟢 GET /api/users/all                                     ┃
┃    🟢 GET /api/users/{id}                                    ┃
┃    🔵 POST /api/users/register                               ┃
┃    🟠 PUT /api/users/{id}                                    ┃
┃    🔴 DELETE /api/users/{id}                                 ┃
┃                                                              ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

✅ **Si ves esto, ¡está funcionando!**

---

## ⚡ PASO 2: Prueba un GET Sencillo (1 minuto)

Vamos a probar el endpoint más fácil primero: **obtener todos los productos**

### 2.1 Busca este texto:
```
🟢 GET /api/productos
```

### 2.2 Haz clic en él

La sección se expandirá:

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ 🟢 GET /api/productos                                        ┃
┃ Listar productos                                              ┃
┃                                                              ┃
┃  📋 Consigue todos los productos (sin necesidad de login)    ┃
┃                                                              ┃
┃ [Try it out]    [Download]                                   ┃
┃                                                              ┃
┃ Parámetros: (ninguno requerido)                              ┃
┃                                                              ┃
┃ Respuestas:                                                  ┃
┃ ✅ 200 - successful operation [application/json]             ┃
┃ ❌ 401 - unauthorized [application/json]                     ┃
┃ ❌ 403 - forbidden [application/json]                        ┃
┃                                                              ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

### 2.3 Haz clic en el botón **"Try it out"** (azul)

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃                                                              ┃
┃ 🟢 GET /api/productos                                        ┃
┃                                                              ┃
┃ [Cancel]  [Execute] ⬅️ CLICK AQUÍ                           ┃
┃                                                              ┃
┃ (No hay parámetros que llenar para GET /productos)           ┃
┃                                                              ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

### 2.4 Haz clic en **"Execute"** (azul)

### 2.5 Mira la respuesta abajo

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ Response                                                    ┃
┃                                                              ┃
┃ Code: 200                                                    ┃
┃ Status: OK                                                   ┃
┃                                                              ┃
┃ Response body:                                               ┃
┃ {                                                            ┃
┃   "success": true,                                           ┃
┃   "message": "Productos obtenidos correctamente",            ┃
┃   "data": []                                                 ┃
┃ }                                                            ┃
┃                                                              ┃
┃ Response headers:                                            ┃
┃ content-type: application/json                              ┃
┃ transfer-encoding: chunked                                  ┃
┃                                                              ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

✅ **¡Éxito! Acabas de hacer tu primera petición.**

La respuesta dice:
- `"success": true` - ✅ Sin errores
- `"data": []` - El array está vacío porque aún no creamos productos

---

## ⚡ PASO 3: Registra un Usuario (2 minutos)

Ahora vamos a crear un usuario.

### 3.1 Busca este texto:
```
🔵 POST /api/users/register
```

### 3.2 Haz clic en él

Se expandirá:

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ 🔵 POST /api/users/register                                  ┃
┃ Registrar un nuevo usuario                                   ┃
┃                                                              ┃
┃ [Try it out]    [Download]                                   ┃
┃                                                              ┃
┃ Request body: *                                              ┃
┃                                                              ┃
┃ Respuestas:                                                  ┃
┃ ✅ 201 - created [application/json]                          ┃
┃ ❌ 400 - invalid input [application/json]                    ┃
┃                                                              ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

### 3.3 Haz clic en **"Try it out"**

Verás un área donde escribir:

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃                                                              ┃
┃ [Cancel]  [Execute]                                          ┃
┃                                                              ┃
┃ Request body: *                                              ┃
┃                                                              ┃
┃ ┌──────────────────────────────────────────────────────┐   ┃
┃ │ {                                                    │   ┃
┃ │   "email": "user@example.com",                       │   ┃
┃ │   "name": "string",                                  │   ┃
┃ │   "password": "string",                              │   ┃
┃ │   "username": "string"                               │   ┃
┃ │ }                                                    │   ┃
┃ │                                    ⬆️ REEMPLAZA ESTO │   ┃
┃ └──────────────────────────────────────────────────────┘   ┃
┃                                                              ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

### 3.4 Borra el contenido y escribe tus datos

Borra todo lo que está en la caja y escribe:

```json
{
  "username": "miadmin",
  "password": "Admin@123",
  "name": "Mi Admin",
  "email": "admin@restaurante.com"
}
```

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃                                                              ┃
┃ Request body:                                                ┃
┃                                                              ┃
┃ ┌──────────────────────────────────────────────────────┐   ┃
┃ │ {                                                    │   ┃
┃ │   "username": "miadmin",                             │   ┃
┃ │   "password": "Admin@123",                           │   ┃
┃ │   "name": "Mi Admin",                                │   ┃
┃ │   "email": "admin@restaurante.com"                   │   ┃
┃ │ }                                                    │   ┃
┃ └──────────────────────────────────────────────────────┘   ┃
┃                                                              ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

### 3.5 Haz clic en **"Execute"**

### 3.6 Mira la respuesta

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ Response                                                    ┃
┃                                                              ┃
┃ Code: 201                                                    ┃
┃ Status: Created                                              ┃
┃                                                              ┃
┃ Response body:                                               ┃
┃ {                                                            ┃
┃   "id": 1,                                                   ┃
┃   "username": "miadmin",                                     ┃
┃   "name": "Mi Admin",                                        ┃
┃   "email": "admin@restaurante.com",                          ┃
┃   "roles": [                                                 ┃
┃     {                                                        ┃
┃       "id": 1,                                               ┃
┃       "rol": "USER",                                         ┃
┃       "descripcion": "Usuario estándar del sistema"          ┃
┃     }                                                        ┃
┃   ]                                                          ┃
┃ }                                                            ┃
┃                                                              ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

✅ **¡Excelente! El usuario se creó!**

Nota: 
- `"id": 1` - Tu usuario tiene ID 1
- `"roles": ["USER"]` - Se le asignó automáticamente el rol USER

---

## ⚡ PASO 4: Haz Login (1 minuto)

Ahora vamos a obtener un token para poder hacer operaciones protegidas.

### 4.1 Busca:
```
🔵 POST /auth/login
```

### 4.2 Haz clic en él y luego en "Try it out"

### 4.3 Escribe tus credenciales:

```json
{
  "username": "miadmin",
  "password": "Admin@123"
}
```

### 4.4 Haz clic en "Execute"

### 4.5 Mira la respuesta

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ Response                                                    ┃
┃                                                              ┃
┃ Code: 200                                                    ┃
┃ Status: OK                                                   ┃
┃                                                              ┃
┃ Response body:                                               ┃
┃ {                                                            ┃
┃   "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWFkbWluI...", ┃
┃   "username": "miadmin",                                     ┃
┃   "message": "Login exitoso"                                 ┃
┃ }                                                            ┃
┃                                                              ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

### ⚠️ IMPORTANTE: COPIA EL TOKEN

El token es esta parte larga:
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWFkbWluI...
```

**Puedes hacer clic en el ícono 📋 para copiarlo automáticamente.**

---

## ⚡ PASO 5: Autoriza con tu Token (1 minuto)

Ahora le diremos a Swagger que use tu token en todas las peticiones siguientes.

### 5.1 Busca el botón **"Authorize"** en la parte superior derecha

```
         ┌─────────────┐
         │ Authorize 🔒 │  ⬅️ HABLAMOS DE ESTE
         └─────────────┘
```

### 5.2 Haz clic en él

Se abrirá una ventana:

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃ Authorization                                          ┃
┃                                                        ┃
┃ Bearer Authentication                                  ┃
┃                                                        ┃
┃ ┌──────────────────────────────────────────────────┐ ┃
┃ │ (Paste your JWT token here)                      │ ┃
┃ │                                                  │ ┃
┃ │ _________________________                         │ ┃
┃ │                   ⬆️ HABLAMOS DE AQUÍ            │ ┃
┃ └──────────────────────────────────────────────────┘ ┃
┃                                                        ┃
┃ [Authorize]  [Close]                                  ┃
┃                                                        ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

### 5.3 En ese campo, escribe:

```
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWFkbWluI...
```

⚠️ **IMPORTANTE:** Incluye `Bearer ` (con espacio) al principio.

```
┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
┃                                                        ┃
┃ ┌──────────────────────────────────────────────────┐ ┃
┃ │ Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWFkb...│ ┃
┃ └──────────────────────────────────────────────────┘ ┃
┃                                                        ┃
┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
```

### 5.4 Haz clic en **"Authorize"** (el botón azul)

Deberías ver:

```
┌──────────────────────────────┐
│ Authorized ✓                 │
│ Successfully authorized      │
└──────────────────────────────┘
```

### 5.5 Haz clic en **"Close"**

---

## ⚡ PASO 6: Ahora Puedes Hacer Peticiones Protegidas

Ahora Swagger enviará automáticamente tu token en todas las peticiones.

### 6.1 Busca:
```
🟢 GET /api/users/profile
```

### 6.2 Haz clic en él, luego "Try it out", luego "Execute"

### 6.3 Deberías ver tu perfil:

```json
{
  "id": 1,
  "username": "miadmin",
  "name": "Mi Admin",
  "email": "admin@restaurante.com",
  "roles": [
    {
      "id": 1,
      "rol": "USER",
      "descripcion": "Usuario estándar del sistema"
    }
  ]
}
```

✅ **¡Ahora tienes autenticación!**

---

## ⚡ RESUMEN: Lo que Acabas de Aprender

```
┌────────────────────────────────────────────────────────────────────┐
│                                                                    │
│ ✅ PASO 1: Abriste Swagger UI                                      │
│ ✅ PASO 2: Probaste un endpoint GET (sin datos)                    │
│ ✅ PASO 3: Probaste un endpoint POST (creando usuario)            │
│ ✅ PASO 4: Hiciste login para obtener token                        │
│ ✅ PASO 5: Autorizaste Swagger con tu token                        │
│ ✅ PASO 6: Probaste un endpoint protegido (GET /users/profile)    │
│                                                                    │
│ Ahora puedes probar TODOS los endpoints.                           │
│                                                                    │
└────────────────────────────────────────────────────────────────────┘
```

---

## 🎯 Próximas Pruebas (Hazlas Tú Mismo)

Ahora que entiendes cómo funciona, intenta:

1. **Crear una categoría:**
   - POST /api/categorias
   - Nombre: "Bebidas"
   - Descripción: "Bebidas frías y calientes"

2. **Crear un producto:**
   - POST /api/productos
   - Nombre: "Agua"
   - Precio: 2.50
   - Categoría ID: 1 (la que acabas de crear)

3. **Actualizar el producto:**
   - PUT /api/productos/1
   - Cambia el precio a 3.00

4. **Eliminar el producto:**
   - DELETE /api/productos/1

---

## 💡 Tips Útiles

1. **Copiar Token Rápido:**
   - Click en el ícono 📋 junto al token en la respuesta de login

2. **Ver Formato de Solicitud:**
   - Haz clic en "curl" para ver el comando equivalente en terminal

3. **Cambiar el Servidor:**
   - Arriba a la izquierda puedes seleccionar diferentes servidores (si los tienes)

4. **Ver Modelos:**
   - Scrollea al final de la página para ver las definiciones de objetos

5. **Desautorizar:**
   - Click en "Authorize" y luego en el botón de logout (🚪)

---

## ✨ ¡Ya Dominas Swagger UI!

¡Felicitaciones! Ahora eres capaz de:
- ✅ Probar cualquier endpoint
- ✅ Crear usuarios y productos
- ✅ Usar autenticación JWT
- ✅ Entender respuestas del servidor

¡Puedes sentirte seguro para desarrollar tu frontend! 🚀
