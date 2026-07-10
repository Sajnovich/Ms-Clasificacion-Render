CREATE TABLE clasificaciones (
    id BIGSERIAL PRIMARY KEY,
    pegi VARCHAR(20) NOT NULL,
    esrb VARCHAR(20) NOT NULL,
    edad_minima INT NOT NULL,
    nombre VARCHAR(120) NOT NULL,
    descripcion VARCHAR(500) NOT NULL
);
