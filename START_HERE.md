# 🚀 INICIA AQUÍ - Primeros Pasos

## Paso 1: Verifica los requisitos

```bash
# Verifica que Java 21 está instalado
java -version

# Verifica que Maven está instalado
mvn -version

# Verifica que MySQL está corriendo
mysql -u root -p
# (Debería permitirte conectar)
```

## Paso 2: Crea la base de datos

```sql
CREATE DATABASE appweb;
```

O desde línea de comandos:
```bash
mysql -u root -p -e "CREATE DATABASE appweb;"
```

## Paso 3: Inicia el backend

Desde la carpeta del proyecto:
```bash
mvn spring-boot:run
```

Deberías ver algo como:
```
Tomcat started on port(s): 8080 (http)
```

## Paso 4: Prueba que funciona

En tu navegador, abre:
```
http://localhost:8080/swagger-ui.html
```

Deberías ver una interfaz con todos tus endpoints.

---

## ✅ Si todo funcionó correctamente

Tu backend está **100% listo para usar con el frontend**.

### Próximos pasos:

1. **Aprende a probar endpoints (IMPORTANTE):**
   - 📖 Lee **SWAGGER_VISUAL_TUTORIAL.md** ← Lee esto primero
   - ❓ Preguntas? Lee **SWAGGER_FAQ.md**
   - 📚 Documentación detallada: **SWAGGER_TUTORIAL.md**

2. **Crea tu proyecto frontend:**
   - React: `npm create vite@latest my-app -- --template react`
   - Vue: `npm create vite@latest my-app -- --template vue`
   - Angular: `ng new my-app`

3. **Conecta tu frontend a este backend:**
   - URL base: `http://localhost:8080`
   - Ve el archivo `FRONTEND_INTEGRATION.md` para ejemplos

4. **Implementa el flujo de login:**
   - POST `/auth/login` → Recibe token JWT
   - Guarda el token en localStorage
   - Usa el token en solicitudes autenticadas

---

## 📚 Documentación (En Orden de Lectura)

1. **START_HERE.md** ← Estás aquí 👈
2. **SWAGGER_VISUAL_TUTORIAL.md** ← Lee esto para aprender Swagger 📖
3. **SWAGGER_FAQ.md** - Preguntas frecuentes sobre Swagger ❓
4. **SWAGGER_TUTORIAL.md** - Documentación completa de Swagger 📚
5. **README.md** - Documentación completa del proyecto
6. **FRONTEND_INTEGRATION.md** - Cómo conectar tu frontend
7. **CHECKLIST.md** - Verificación completa
8. **SUMMARY.md** - Resumen técnico

---

## ❓ Problemas comunes

### "Connection refused"
- ✅ Verifica que MySQL está corriendo
- ✅ Verifica credenciales en `application.properties`

### "Base de datos no encontrada"
- ✅ Crea la BD: `CREATE DATABASE appweb;`

### "Swagger no carga"
- ✅ Espera a que Tomcat inicie completamente
- ✅ Recarga la página

### "CORS error"
- ✅ Tu frontend URL debe estar en `allowedOrigins`
- ✅ Por defecto acepta localhost:3000 y localhost:4200

### "No entiendo cómo probar endpoints"
- ✅ Lee **SWAGGER_VISUAL_TUTORIAL.md** - Te lo explica paso a paso

---

## 🎯 Resumen

| Paso | Comando | Resultado |
|------|---------|-----------|
| 1 | `java -version` | Verifica Java 21 |
| 2 | `mysql -u root -p -e "CREATE DATABASE appweb;"` | BD creada |
| 3 | `mvn spring-boot:run` | Backend en puerto 8080 |
| 4 | Abre http://localhost:8080/swagger-ui.html | API documentation |
| 5 | Lee SWAGGER_VISUAL_TUTORIAL.md | Aprende a probar endpoints |
| 6 | Crea frontend y conecta | ¡Listo! |

---

## 🎉 ¡Ahora estás listo!

Tu backend está funcionando. 

**Próximo paso:** Lee **SWAGGER_VISUAL_TUTORIAL.md** para aprender a probar tus endpoints.

Después puedes proceder a crear tu frontend.

Para más detalles, lee los otros archivos .md en la carpeta.
