create database reto3;
use reto3;
CREATE TABLE Cine (
idCine VARCHAR(10) PRIMARY KEY,
nombreCine VARCHAR(25) NOT NULL,
numSala INTEGER
);

CREATE TABLE Cliente(
DNI VARCHAR(9) PRIMARY KEY,
usuario VARCHAR(20) NOT NULL UNIQUE,
nombreCliente VARCHAR(20) NOT NULL,
apellidos VARCHAR(45) NOT NULL,
sexo CHAR(1) NOT NULL CHECK (sexo='H' OR sexo='M'),
contrasena VARCHAR(20) NOT NULL
)ENGINE='InnoDB' DEFAULT CHARSET=LATIN1;

CREATE TABLE Pelicula(
codPelicula INTEGER AUTO_INCREMENT PRIMARY KEY,
nombrePelicula VARCHAR(50) NOT NULL,
duracion INTEGER NOT NULL,
genero SET ('Accion','Aventura','Terror','Drama','Comedia','Ciencia-Ficcion','Suspense') NOT NULL 
);

CREATE TABLE Sala(
nombreSala varchar(20),
idCine VARCHAR(10),
CONSTRAINT PF_sala PRIMARY KEY (idCine,nombreSala),
CONSTRAINT FK_id_Cine FOREIGN KEY (idCine) REFERENCES Cine(idCine) 
ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Emision(
idEmision INTEGER AUTO_INCREMENT PRIMARY KEY,
FechaEmision DATE NOT NULL,
HoraEmision TIME NOT NULL,
precioInicial FLOAT NOT NULL DEFAULT 7,
idCine VARCHAR(10),
nombreSala VARCHAR (20),
codPelicula INTEGER,
CONSTRAINT FK_id_CineSala FOREIGN KEY (idCine,nombreSala) REFERENCES Sala(idCine,nombreSala)
ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_cod_Pelicula FOREIGN KEY (codPelicula) REFERENCES Pelicula(codPelicula)
ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Compra(
codCompra INTEGER AUTO_INCREMENT PRIMARY KEY,
precioTotal FLOAT NOT NULL,
descuento INTEGER NOT NULL DEFAULT 0,
horaCompra DATETIME NOT NULL,
DNI VARCHAR(9),
CONSTRAINT FK_DNIC FOREIGN KEY (DNI) REFERENCES Cliente(DNI)
)ENGINE='InnoDB' DEFAULT CHARSET=LATIN1;

CREATE TABLE Entrada(
codEntrada INTEGER AUTO_INCREMENT PRIMARY KEY,
precioFinal FLOAT NOT NULL,
idEmision INTEGER,
codCompra INTEGER,
CONSTRAINT FK_id_Emision FOREIGN KEY (idEmision) REFERENCES Emision(idEmision),
CONSTRAINT FK_codCompra FOREIGN KEY (codCompra) REFERENCES  Compra(CodCompra)
);

INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('Handia','160','Drama');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('La lista de Schindler','197','Drama');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('Cadena Perpetua','160','Drama');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('Million Dollar Baby','160','Drama');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('2001: Odisea en el espacio','139','Ciencia-Ficcion');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('El planeta de los Simios','112','Ciencia-Ficcion');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('Alien','116','Terror');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('Psicosis','109','Terror');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('El resplandor','146','Terror');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('Dracula','130','Drama');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('Cisne Negro','85','Drama');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('La vida de Brian','93','Comedia');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('Scary Movie 2','83','Comedia');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('El Gran Lebowski','117','Comedia');
INSERT INTO pelicula(nombrePelicula,duracion,genero) VALUE ('Aterriza como puedas','88','Comedia');

INSERT INTO cine VALUES ('GC','Cines Golem','6');
INSERT into cine VALUES ('YC','Yelmo Cines','7');
INSERT into cine VALUES ('ZB','Cines Zubiarte','8');

INSERT into Cliente VALUES ('12318312Q','igor1','Igor','Bueno','H',(aes_encrypt('1234567A','AES')));
INSERT into Cliente VALUES ('21918362X','elorrieta1','Elorrieta','Centro','H',(aes_encrypt('elorrieta00','AES')));
INSERT into Cliente VALUES ('43240843O','sixseiger','Erlantz','Gil','H',(aes_encrypt('987654B','AES')));
INSERT into Cliente VALUES ('79114209Q','akos','Unai','Cano','H',(aes_encrypt('uwuowo2','AES')));

INSERT INTO Sala VALUE ('Sala 1','GC');
INSERT INTO Sala VALUE ('Sala 2','GC');
INSERT INTO Sala VALUE ('Sala 3','GC');
INSERT INTO Sala VALUE ('Sala 4','GC');
INSERT INTO Sala VALUE ('Sala 5','GC');
INSERT INTO Sala VALUE ('Sala 6','GC');
INSERT INTO Sala VALUE ('Sala 1','ZB');
INSERT INTO Sala VALUE ('Sala 2','ZB');
INSERT INTO Sala VALUE ('Sala 3','ZB');
INSERT INTO Sala VALUE ('Sala 4','ZB');
INSERT INTO Sala VALUE ('Sala 5','ZB');
INSERT INTO Sala VALUE ('Sala 6','ZB');
INSERT INTO Sala VALUE ('Sala 7','ZB');
INSERT INTO Sala VALUE ('Sala 8','ZB');
INSERT INTO Sala VALUE ('Sala 1','YC');
INSERT INTO Sala VALUE ('Sala 2','YC');
INSERT INTO Sala VALUE ('Sala 3','YC');
INSERT INTO Sala VALUE ('Sala 4','YC');
INSERT INTO Sala VALUE ('Sala 5','YC');
INSERT INTO Sala VALUE ('Sala 6','YC');
INSERT INTO Sala VALUE ('Sala 7','YC');
