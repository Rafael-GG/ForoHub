# 🌐 ForoHub - API REST de Foro de Discusión

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-6DB33F?style=for-the-badge&logo=spring-boot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13+-336791?style=for-the-badge&logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

**Una API REST completa para gestionar un foro de discusión con autenticación JWT, validaciones robustas y documentación interactiva**

[Características](#características) • [Tecnologías](#tecnologías) • [Instalación](#instalación) • [Configuración](#configuración) • [Endpoints](#endpoints) • [Estructura](#estructura)

</div>

---

## 📋 Descripción

**ForoHub** es una aplicación backend desarrollada con **Spring Boot** que implementa un foro de discusión completo. Los usuarios pueden crear tópicos, responder a ellos, gestionar su perfil y todo esto está protegido con autenticación basada en **JWT (JSON Web Tokens)**.

El proyecto utiliza las mejores prácticas de desarrollo con Spring Boot, incluyendo validación de datos, manejo de excepciones global, seguridad con contraseñas encriptadas y documentación automática de API con Swagger.

---

## ✨ Características

- ✅ **Autenticación y Autorización** - Login con generación de JWT
- ✅ **Gestión de Usuarios** - Registro, actualización y desactivación de cuentas
- ✅ **Creación de Tópicos** - Los usuarios pueden crear temas de discusión
- ✅ **Respuestas a Tópicos** - Sistema completo de respuestas y debate
- ✅ **Estados de Tópicos** - ACTIVE, INACTIVE, RESOLVED
- ✅ **Validaciones Robustas** - Datos validados en frontend y backend
- ✅ **Manejo de Errores Global** - Respuestas de error consistentes
- ✅ **Documentación Interactiva** - Swagger UI para explorar la API
- ✅ **CORS Configurado** - Soporte para solicitudes cross-origin
- ✅ **Migraciones de Base de Datos** - Flyway para versionado automático
- ✅ **Pool de Conexiones** - HikariCP para optimización de conexiones

---

## 🛠️ Tecnologías

### Backend
| Tecnología | Versión | Descripción |
|-----------|---------|------------|
| **Java** | 21 | Lenguaje de programación |
| **Spring Boot** | 3.3.0 | Framework principal |
| **Spring Security** | 6.1.8 | Autenticación y autorización |
| **Spring Data JPA** | 3.3.0 | ORM y acceso a datos |
| **Hibernate** | 6.4.4 | Mapeo objeto-relacional |

### Base de Datos
| Tecnología | Versión | Descripción |
|-----------|---------|------------|
| **PostgreSQL** | 13+ | Base de datos relacional |
| **Flyway** | 9.22.3 | Migraciones de base de datos |
| **HikariCP** | 5.1.0 | Pool de conexiones |

### Seguridad
| Tecnología | Versión | Descripción |
|-----------|---------|------------|
| **JWT (java-jwt)** | 4.2.1 | Tokens de autenticación |
| **BCrypt** | Nativo | Encriptación de contraseñas |

### Documentación
| Tecnología | Versión | Descripción |
|-----------|---------|------------|
| **SpringDoc OpenAPI** | 2.5.0 | Documentación y Swagger UI |

### Herramientas
| Herramienta | Descripción |
|------------|-----------|
| **Maven** | Gestor de dependencias y build |
| **Lombok** | Reducción de código boilerplate |
| **Spring DevTools** | Recarga en caliente durante desarrollo |

---

## 📋 Requisitos Previos

Antes de instalar ForoHub, asegúrate de tener instalado:

- **Java 21+** - [Descargar](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.8+** - [Descargar](https://maven.apache.org/download.cgi)
- **PostgreSQL 13+** - [Descargar](https://www.postgresql.org/download/)
- **Git** - [Descargar](https://git-scm.com/downloads)

### Verificar Instalación

```bash
java -version
mvn -version
psql --version
git --version
```

---

## 📦 Instalación

### 1. Clonar el Repositorio

```bash
cd /ruta/deseada
git clone https://github.com/tu-usuario/forohub.git
cd forohub
```

### 2. Crear Base de Datos en PostgreSQL

```bash
# Acceder a PostgreSQL
psql -U postgres

# Crear usuario y base de datos
CREATE USER `tu_usuario` WITH PASSWORD 'tu_contraseña';
CREATE DATABASE `tu_base_de_datos` OWNER `tu_usuario`;

# Salir
\q
```

### 3. Compilar el Proyecto

```bash
mvn clean install
```

### 4. Iniciar la Aplicación

```bash
mvn spring-boot:run
```

O ejecutar desde la clase principal:

```bash
java -cp target/forohub-0.0.1-SNAPSHOT.jar com.miproyecto.forohub.ChallengeForoHubApplication
```

### ✅ Verificación de Instalación

Si la aplicación se inicia correctamente, deberías ver:

```
Tomcat started on port(s): 8080 (http) with context path ''
```

---

## ⚙️ Configuración

### application.properties
Es necesario que renombres el archivo `application.properties.example` a `application.properties` o crealo tu mismo y copia en el las configuraciones necesarias.
El archivo `src/main/resources/application.properties.example` contiene las configuraciones necesarias:

```properties
# Nombre de la aplicación
spring.application.name=forohub

# Puerto del servidor
server.port=8080

# Configuración de PostgreSQL
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

# Pool de conexiones HikariCP
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.max-lifetime=1800000

# Configuración JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true
spring.jpa.hibernate.format_sql=true
spring.jpa.open-in-view=false

# Manejo de errores
server.error.include-stacktrace=never
```

### Cambiar Credenciales de Base de Datos

Para conectarte a tu base de datos, modifica:

```properties
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.url=jdbc:postgresql://host:puerto/nombre_bd
```

---

## 🔐 Autenticación y Seguridad

### Flujo de Autenticación

1. **Registro**: El usuario se registra con email, nombre de usuario y contraseña
2. **Login**: El usuario envía sus credenciales
3. **Token JWT**: La API devuelve un token JWT válido por cierto tiempo
4. **Protección**: Todos los endpoints excepto `/login` y `/registro` requieren el token

### Estructura del JWT

El token JWT incluye:
- User ID
- Fecha de emisión
- Fecha de expiración
- Firma con secret key

### Cómo Usar el Token

En cualquier solicitud protegida, agrega el header:

```
Authorization: Bearer <tu-token-jwt>
```

### Encriptación de Contraseñas

Las contraseñas se encriptan con **BCrypt**, nunca se almacenan en texto plano.

---

## 📡 Endpoints

### Autenticación

#### Login
```http
POST /login
Content-Type: application/json

{
  "email": "usuario@example.com",
  "password": "contraseña123"
}

Response 200:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tipo": "Bearer"
}
```

#### Registro
```http
POST /registro
Content-Type: application/json

{
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "username": "jperez",
  "password": "contraseña123"
}

Response 201: Usuario registrado exitosamente
```

### Usuarios

#### Listar Todos los Usuarios
```http
GET /usuarios
Authorization: Bearer <token>

Response 200: [
  {
    "id": 1,
    "name": "Juan Pérez",
    "email": "juan@example.com",
    "username": "jperez"
  }
]
```

#### Obtener Usuario por ID
```http
GET /usuarios/{id}
Authorization: Bearer <token>

Response 200: { id, name, email, username }
```

#### Actualizar Usuario
```http
PUT /usuarios/{id}
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Juan Carlos",
  "email": "juancarlos@example.com"
}

Response 200: Usuario actualizado
```

#### Desactivar Usuario
```http
DELETE /usuarios/{id}
Authorization: Bearer <token>

Response 200/204: Usuario desactivado
```

### Tópicos

#### Crear Tópico
```http
POST /topicos
Authorization: Bearer <token>
Content-Type: application/json

{
  "title": "¿Cómo usar Spring Boot?",
  "message": "Necesito ayuda para entender Spring Boot...",
  "course": "Spring Boot"
}

Response 201: Tópico creado
```

#### Listar Tópicos
```http
GET /topicos
Authorization: Bearer <token>

Response 200: [
  {
    "id": 1,
    "title": "¿Cómo usar Spring Boot?",
    "message": "...",
    "date": "2026-03-05T...",
    "status": "ACTIVE",
    "author": "Juan Pérez",
    "course": "Spring Boot"
  }
]
```

#### Obtener Tópico por ID
```http
GET /topicos/{id}
Authorization: Bearer <token>

Response 200: { id, title, message, date, status, author, course }
```

#### Actualizar Tópico
```http
PUT /topicos/{id}
Authorization: Bearer <token>
Content-Type: application/json

{
  "title": "Nuevo título",
  "message": "Nuevo mensaje",
  "status": "RESOLVED"
}

Response 200: Tópico actualizado
```

#### Eliminar Tópico
```http
DELETE /topicos/{id}
Authorization: Bearer <token>

Response 200/204: Tópico eliminado
```

### Respuestas

#### Crear Respuesta
```http
POST /topicos/{topicoId}/respuestas
Authorization: Bearer <token>
Content-Type: application/json

{
  "solution": "La solución es usar @Spring Boot..."
}

Response 201: Respuesta creada
```

#### Listar Respuestas de un Tópico
```http
GET /topicos/{topicoId}/respuestas
Authorization: Bearer <token>

Response 200: [ { id, solution, author, date, topico } ]
```

#### Actualizar Respuesta
```http
PUT /respuestas/{id}
Authorization: Bearer <token>
Content-Type: application/json

{
  "solution": "Solución mejorada..."
}

Response 200: Respuesta actualizada
```

#### Eliminar Respuesta
```http
DELETE /respuestas/{id}
Authorization: Bearer <token>

Response 200/204: Respuesta eliminada
```

---

## 🗄️ Modelo de Datos

### Tabla: Usuarios

| Campo | Tipo | Descripción |
|-------|------|------------|
| `id` | BIGINT | Identificador único (PK) |
| `name` | VARCHAR(255) | Nombre completo |
| `email` | VARCHAR(255) | Email único |
| `username` | VARCHAR(255) | Nombre de usuario único |
| `password` | VARCHAR(255) | Contraseña encriptada |
| `active` | BOOLEAN | Estado de la cuenta |

### Tabla: Tópicos

| Campo | Tipo | Descripción |
|-------|------|------------|
| `id` | BIGINT | Identificador único (PK) |
| `title` | VARCHAR(255) | Título del tópico |
| `message` | TEXT | Contenido del tópico |
| `date` | TIMESTAMP | Fecha de creación |
| `status` | VARCHAR(20) | ACTIVE, INACTIVE, RESOLVED |
| `author_id` | BIGINT | ID del autor (FK) |
| `course` | VARCHAR(255) | Categoría/Curso |
| `active` | BOOLEAN | Estado del tópico |

### Tabla: Respuestas

| Campo | Tipo | Descripción |
|-------|------|------------|
| `id` | BIGINT | Identificador único (PK) |
| `creationDate` | TIMESTAMP | Fecha de creación |
| `solution` | TEXT | Contenido de la respuesta |
| `author` | BIGINT | ID del autor (FK) |
| `topico` | BIGINT | ID del tópico (FK) |
| `active` | BOOLEAN | Estado de la respuesta |

---

## 🚀 Uso de la API

### Con cURL

```bash
# Login
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@example.com",
    "password": "contraseña123"
  }'

# Crear tópico (con token)
curl -X POST http://localhost:8080/topicos \
  -H "Authorization: Bearer <tu-token>" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Mi pregunta",
    "message": "Necesito ayuda...",
    "course": "Spring Boot"
  }'

# Listar tópicos
curl -X GET http://localhost:8080/topicos \
  -H "Authorization: Bearer <tu-token>"
```

### Con Postman

1. Abre Postman
2. Importa la colección desde `/docs/ForoHub.postman_collection.json`
3. Establece el token JWT en las variables de entorno
4. Ejecuta las solicitudes

### Con cliente REST en VS Code

Instala la extensión "REST Client" y usa archivos `.http`:

```http
@baseUrl = http://localhost:8080
@token = tu-jwt-token

### Login
POST {{baseUrl}}/login
Content-Type: application/json

{
  "email": "usuario@example.com",
  "password": "contraseña123"
}

###
POST {{baseUrl}}/topicos
Authorization: Bearer {{token}}
Content-Type: application/json

{
  "title": "Mi pregunta",
  "message": "Contenido...",
  "course": "Spring Boot"
}
```

---

## 📖 Documentación Interactiva

Una vez que la aplicación esté corriendo, accede a la documentación Swagger:

```
http://localhost:8080/swagger-ui.html
```

Aquí puedes:
- Ver todos los endpoints disponibles
- Probar los endpoints directamente
- Ver los esquemas de request/response
- Entender los códigos de respuesta

También puedes acceder al JSON de OpenAPI:
```
http://localhost:8080/v3/api-docs
```

---

## 📝 Mejores Prácticas Implementadas

✅ **Separación de Responsabilidades**
- Controllers manejan las solicitudes HTTP
- Services contienen la lógica de negocio
- Repositories acceden a los datos
- DTOs para transferencia de datos

✅ **Validación de Datos**
- Validaciones en anotaciones (javax.validation)
- Validaciones personalizadas en servicios
- Respuestas de error detalladas

✅ **Seguridad**
- Contraseñas encriptadas con BCrypt
- Autenticación con JWT
- Autorización en endpoints
- CSRF deshabilitado (API stateless)

✅ **Manejo de Errores**
- GlobalExceptionHandler centralizado
- Códigos HTTP apropiados
- Mensajes de error consistentes

✅ **Documentación**
- Swagger/OpenAPI automático
- Comentarios en el código
- Este README completo

---

## 📞 Contacto

Si tienes preguntas o sugerencias, no dudes en abrir un issue o contactarme - 
[GitHub](https://github.com/Rafael-GG) - [LinkedIn](https://www.linkedin.com/in/rafael-gerson-gimenez)

---

<div align="center">

**Hecho con ❤️ por Rafael-GG**

</div>
