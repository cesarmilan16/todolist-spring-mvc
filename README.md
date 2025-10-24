# 📝 ToDo List - Spring Boot

Aplicación web de lista de tareas con autenticación de usuarios.

## 🚀 Características
- Crear, completar y eliminar tareas
- Autenticación de usuarios
- Interfaz responsive
- Base de datos MySQL

## 🛠️ Tecnologías
- Spring Boot MVC
- Thymeleaf
- Hibernate/JPA
- MySQL
- Docker

# 🐳 Despliegue Rápido

```bash
# Ejecutar con Docker
docker-compose up --build -d

# La app estará en: http://localhost:8081
```

---

## 📦 Servicios

Aplicación: puerto `8081`  
MySQL: puerto `3307`

---

## 🗃️ Base de Datos

Se crean automáticamente:

- Tabla **user** (usuarios)  
- Tabla **task** (tareas)

---

## 🔧 Comandos Útiles

```bash
docker-compose up -d          # Iniciar
docker-compose down           # Detener
docker-compose logs -f        # Ver logs
```

---

¡Listo para usar!

## 📄 Licencia

Este proyecto está bajo la licencia [MIT](./LICENSE).  
Consulta el archivo `LICENSE` para más detalles.
