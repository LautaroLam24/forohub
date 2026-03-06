# 📌 ForoHub API

API REST desarrollada con **Spring Boot** para la gestión de tópicos de un foro.

Permite realizar operaciones **CRUD completas sobre tópicos** e incluye **autenticación y autorización mediante JWT (JSON Web Token)** para proteger los endpoints.

Este proyecto fue desarrollado como parte del **challenge ForoHub del programa Oracle Next Education (Alura Latam)**.

---

# 🚀 Funcionalidades

La API permite:

- Crear nuevos tópicos
- Listar tópicos con paginación
- Consultar el detalle de un tópico
- Actualizar un tópico existente
- Eliminar un tópico

Además incluye:

- Autenticación con **Spring Security + JWT**
- Persistencia con **Spring Data JPA**
- Base de datos **MySQL**
- Migraciones de base de datos con **Flyway**
- Validaciones con **Jakarta Validation**

---

# 🛠 Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (Auth0 Java JWT)
- MySQL
- Flyway
- Maven
- Lombok

---

# 📂 Estructura del proyecto

El proyecto está organizado utilizando una **arquitectura por capas**.

src/main/java/com/aluracursos/forohub

├── controller
│ ├── AutenticacionController
│ └── TopicoController

├── domain
│ ├── topico
│ │ ├── DatosActualizacionTopico
│ │ ├── DatosDetalleTopico
│ │ ├── DatosListaTopicos
│ │ ├── DatosRegistroTopico
│ │ ├── StatusTopico
│ │ ├── Topico
│ │ └── TopicoRepository

│ └── usuario
│ ├── AutenticacionService
│ ├── DatosAutenticacion
│ ├── Usuario
│ └── UsuarioRepository

├── infra
│ └── security
│ ├── SecurityConfigurations
│ ├── SecurityFilter
│ └── TokenService

├── springdoc
│ └── SpringDocConfiguration

└── ForohubApplication


---

# 🔐 Autenticación JWT

La API utiliza **JWT (JSON Web Token)** para autenticar a los usuarios y proteger los endpoints.

---

# 🔑 Flujo de autenticación

## 1️⃣ Login

El usuario envía sus credenciales al endpoint:
POST /login

Ejemplo de request:

```json
{
  "username": "usuario",
  "password": "password"
}

2️⃣ Generación del token

Si las credenciales son correctas, la API devuelve un token JWT.

Ejemplo de respuesta:

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

3️⃣ Acceso a endpoints protegidos

Para acceder a los endpoints protegidos se debe enviar el token en el header:

Authorization: Bearer TOKEN

Ejemplo:

Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...


# 📡 Endpoints principales
Crear tópico
POST /topicos

Ejemplo de request:

{
  "titulo": "Problema con Spring",
  "mensaje": "No logro configurar mi aplicación",
  "autor": "Lautaro",
  "curso": "Spring Boot"
}

---

Listar tópicos
GET /topicos
Detalle de tópico
GET /topicos/{id}

Ejemplo:

GET /topicos/1
Actualizar tópico
PUT /topicos/{id}
Eliminar tópico
DELETE /topicos/{id}


#📋 Reglas de negocio implementadas

Todos los campos obligatorios son validados correctamente

No se permite registrar tópicos duplicados con el mismo título y mensaje

Antes de actualizar o eliminar un tópico se valida que el ID exista

Los endpoints están protegidos mediante autenticación JWT

---

#🗄 Base de datos

La base de datos utilizada es MySQL.

Tablas principales:

topicos

usuarios

Las migraciones se gestionan mediante Flyway.

---
