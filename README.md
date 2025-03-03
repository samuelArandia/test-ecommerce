# Test E-Commerce API

## Descripción
Este proyecto es una API REST para la gestión de productos y pedidos en una tienda en línea, desarrollado con **Spring Boot 3.4.3** y **Java 17**.

---

## Tecnologías utilizadas
- **Spring Boot** (Web, Data JPA, Security)
- **PostgreSQL** como base de datos
- **JWT** para autenticación
- **Swagger** para documentación de la API
- **Docker** para la base de datos
- **Maven** para gestión de dependencias

---

## Configuración de Docker
Para ejecutar la base de datos PostgreSQL en un contenedor Docker, se usa la siguiente configuración:

```yaml
version: '3.8'
services:
  db:
    image: postgres:16
    ports:
      - "5430:5432"
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: ecommerce_database
    networks:
      - advisenetwork
networks:
  advisenetwork:
```

---

## Instalación y Ejecución

### 1. Clonar el repositorio
```bash
git clone https://github.com/usuario/test-ecommerce.git
cd test-ecommerce
```

### 2. Construir y ejecutar la aplicación
```bash
mvn clean package
mvn spring-boot:run
```

### 3. Levantar PostgreSQL con Docker
```bash
docker-compose up -d
```

### 4. Acceder a Swagger
Una vez iniciada la aplicación, la documentación de la API estará disponible en:
[Swagger UI](http://localhost:8080/api/swagger-ui.html)

---

## Endpoints Principales

### **Autenticación**
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/auth/register` | Registro de usuarios |
| POST | `/api/auth/authenticate` | Autenticación de usuarios |
| POST | `/api/auth/registerAdmin` | Registro de administradores |

### **Productos y Pedidos** (protegidos por JWT)
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/products` | Listar productos |
| POST | `/api/products` | Crear un producto |
| GET | `/api/orders` | Listar pedidos |
| POST | `/api/orders` | Crear un pedido |

Para acceder a estos endpoints, se debe enviar un **token JWT** en el `Authorization Header`:
```
Authorization: Bearer <TOKEN>
```

---

## Dependencias Principales (Maven)
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.8.5</version>
    </dependency>
</dependencies>
```

---

## Autores
- **Samuel Arandia** - [GitHub](https://github.com/samuelArandia)

