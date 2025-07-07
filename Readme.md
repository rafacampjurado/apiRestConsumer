# 🏗️ API REST CONSUMER
Bienvenidos a mi aplicación de Spring Boot que implementa un API REST para obtener productos similares. Esta aplicación se conecta a dos APIs existentes para obtener los IDs de productos similares y sus detalles.

# 📌ENDPOINTS
Mi aplicación expone un endpoint REST que permite obtener los detalles de productos similares a partir de un ID de producto dado. El endpoint es el siguiente:
```
GET /product/{id}/similar
```

# ✅ REQUERIMIENTOS
Para poder ejecutar esta aplicación, asegúrate de tener instalados los siguientes requisitos:
- Java 17 o superior
- Maven
- Docker
- Docker Compose

# 🚀 INSTRUCCIONES DE EJECUCIÓN
Para ejecutar la aplicación, sigue estos pasos:
1. Clona este repositorio en tu máquina local.
2. Navega al directorio del proyecto.
3. Asegúrate de que Docker y Docker Compose estén instalados y funcionando.
4. Ejecuta el siguiente comando para iniciar los servicios necesarios:
   ```bash
   docker-compose up -d simulado influxdb grafana
   ```
5. Navega a la dirección http://localhost:5000/swagger-ui/index.html o Ejecuta una solicitud GET al endpoint `/product/{id}/similar`, reemplazando `{id}` con el ID del producto que deseas consultar. Por ejemplo:
   ```
   GET http://localhost:5000/product/1/similar
   ```

