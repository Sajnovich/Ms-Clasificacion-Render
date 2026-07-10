# Ms-Clasificacion Render

Version independiente del microservicio de clasificaciones para despliegue en Render.
Administra clasificaciones internacionales de edad para juegos usando PEGI y ESRB.

## Responsabilidad

- Crear clasificaciones.
- Consultar clasificaciones por edad minima.
- Consultar clasificaciones permitidas para una edad.
- Actualizar y eliminar clasificaciones.
- Entregar datos comprensibles para clasificacion internacional.

## Datos tecnicos

| Item | Valor |
| --- | --- |
| Puerto local | `8093` |
| Puerto Render | `${PORT}` |
| Base de datos | PostgreSQL |
| Ruta base | `/api/v2/clasificaciones` |
| HATEOAS | `/api/v2/hateoas/clasificaciones` |
| Swagger local | `http://localhost:8093/doc/swagger-ui.html` |
| Swagger Render | `https://ms-clasificacion-render.onrender.com/doc/swagger-ui.html` |
| Eureka | Deshabilitado por defecto para Render |

## Endpoints principales

- `GET /api/v2/clasificaciones`
- `GET /api/v2/clasificaciones/{id}`
- `GET /api/v2/clasificaciones/edad-minima/{edadMinima}`
- `GET /api/v2/clasificaciones/permitidas`
- `POST /api/v2/clasificaciones`
- `PUT /api/v2/clasificaciones/{id}`
- `DELETE /api/v2/clasificaciones/{id}`

## Datos demo

Incluye `DataInitializer` para cargar clasificaciones base como PEGI y ESRB.

## Variables de entorno para Render

Configurar estas variables en el Web Service:

```text
SPRING_DATASOURCE_URL=jdbc:postgresql://HOST:PORT/DATABASE
SPRING_DATASOURCE_USERNAME=USUARIO
SPRING_DATASOURCE_PASSWORD=PASSWORD
EUREKA_CLIENT_ENABLED=false
```

Render entrega los datos de conexion al crear la base PostgreSQL. Usar el host,
puerto, database, usuario y password de esa base para formar la URL JDBC.

La base debe crearse en la misma region que el Web Service. Para este
despliegue se utiliza PostgreSQL administrado por Render, no una base local ni
un contenedor de base de datos dentro del proyecto.

## Despliegue realizado en Render

1. Crear una instancia PostgreSQL en Render en la misma region del servicio.
2. Crear un Web Service desde este repositorio con entorno `Docker` y rama
   `main`.
3. Configurar las variables de entorno indicadas anteriormente. No es necesario
   definir `PORT`: Render lo entrega automaticamente y Spring Boot lo utiliza.
4. Render construye la imagen con el `Dockerfile`, inicia el servicio y Flyway
   crea el esquema de base de datos.
5. `DataInitializer` carga cinco clasificaciones PEGI y ESRB cuando la tabla se
   encuentra vacia.

URL de produccion:

`https://ms-clasificacion-render.onrender.com/api/v2/clasificaciones`

Swagger de produccion:

`https://ms-clasificacion-render.onrender.com/doc/swagger-ui.html`

## Ejecucion local con PostgreSQL

```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/clasificacion_db \
SPRING_DATASOURCE_USERNAME=postgres \
SPRING_DATASOURCE_PASSWORD=postgres \
EUREKA_CLIENT_ENABLED=false \
./mvnw spring-boot:run
```

## Ejecucion con Docker local

Desde esta carpeta:

```bash
docker build -t ms-clasificacion-render .
docker run --rm -p 8093:8093 \
  -e SERVER_PORT=8093 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/clasificacion_db \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=postgres \
  -e EUREKA_CLIENT_ENABLED=false \
  ms-clasificacion-render
```

## URLs de prueba

- `GET /api/v2/clasificaciones`
- `GET /api/v2/clasificaciones/permitidas?edad=16`
- `GET /doc/swagger-ui.html`
