# Supermarket API

API REST para la gestión de un sistema de supermercado desarrollada con Spring Boot. El proyecto está preparado para ejecutarse en contenedores Docker junto con una base de datos MySQL.

---

## 🚀 Tecnologías utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL 8
* Docker & Docker Compose
* Maven

---

## 📦 Arquitectura

El sistema está compuesto por dos servicios principales:

* **API Spring Boot** → expone los endpoints REST
* **Base de datos MySQL** → almacenamiento persistente

Ambos servicios se orquestan mediante `docker-compose`.

---

## ⚙️ Requisitos previos

* Docker instalado
* Docker Compose instalado
* (Opcional) Maven para desarrollo local

---

## 🐳 Ejecución con Docker

### 1. Construir y levantar el proyecto

```bash
docker compose up --build
```

Esto:

* Construye la imagen de la API
* Descarga MySQL
* Levanta ambos contenedores
* Crea automáticamente la base de datos si no existe

---

## 🗄️ Configuración de base de datos

La aplicación se conecta a MySQL mediante variables de entorno:

```env
DB_URL=jdbc:mysql://db:3306/supermarketsystem?createDatabaseIfNotExist=true&serverTimezone=UTC
DB_USER_NAME=root
DB_PASSWORD=1234
```

---

## 🌐 Acceso a la aplicación

Una vez levantado el sistema:

* API REST:
  `http://localhost:8080`

---

## 📌 Endpoints principales

Ejemplo:

* `GET /api/productos` → listar productos
* `POST /api/productos` → crear producto
* `GET /api/clientes` → listar clientes
* `POST /api/pedidos` → crear pedido

*(Los endpoints pueden variar según la implementación del proyecto)*

---

## 🧱 Persistencia

* Hibernate genera automáticamente las tablas a partir de las entidades.
* La base de datos se inicializa al arrancar el contenedor MySQL.

---

## 🛠️ Ejecución en local (sin Docker)

### 1. Levantar MySQL local

Crear base de datos:

```sql
CREATE DATABASE supermarketsystem;
```

### 2. Ejecutar aplicación

```bash
mvn spring-boot:run
```

---

## 🧪 Perfil de desarrollo

Durante el desarrollo inicial se utilizó H2 como base de datos en memoria.

---

## 📁 Estructura del proyecto

```
supermarketsystem/
 ├── docker-compose.yml
 └── sales-system/
     ├── src/
     ├── Dockerfile
     ├── pom.xml
     └── Jenkinsfile
```

---

## 🔄 Flujo de despliegue

1. Código en Spring Boot
2. Build Maven → genera JAR
3. Docker construye imagen
4. Docker Compose levanta MySQL + API
5. Aplicación lista en localhost:8080

---

## 📌 Notas

* El puerto MySQL interno es `3306`
* El puerto expuesto puede variar (`13306:3306`, `3307:3306`, etc.)
* Spring Boot se ejecuta dentro de Docker, no en el host

---

## 👨‍💻 Autor

Proyecto de práctica para aprendizaje de Spring Boot, Docker y arquitectura backend.