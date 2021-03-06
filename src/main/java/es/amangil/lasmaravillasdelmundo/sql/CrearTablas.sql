CREATE TABLE LOCALIZACION (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    CODIGO CHAR(2),
    NOMBRE VARCHAR(20) NOT NULL,
    CONSTRAINT ID_LOCALIZACION_PK PRIMARY KEY (ID)
);

CREATE TABLE MARAVILLA (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, -- Id autonumérico
    NOMBRE VARCHAR(20) NOT NULL,
    APELLIDOS VARCHAR(40) NOT NULL,-- NO
    LOCALIZACION INTEGER,
    FECHA_DE_CONSTRUCCION DATE,
    NUM_HIJOS SMALLINT,-- NO
    ESTADO_DE_LA_MARAVILLA CHAR(1),
    PRECIO_DE_ENTRADA DECIMAL(7,2),
    SIGUE_EXISTIENDO BOOLEAN,
    FOTO VARCHAR(30),
    CONSTRAINT ID_MARAVILLA_PK PRIMARY KEY (ID),
    CONSTRAINT LOCALIZACION_MARAVILLA_PK_FK FOREIGN KEY (LOCALIZACION) REFERENCES LOCALIZACION (ID)
);