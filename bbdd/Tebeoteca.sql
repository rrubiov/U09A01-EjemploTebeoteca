CREATE DATABASE Tebeoteca;
USE Tebeoteca;

CREATE TABLE Tebeo(
	ISBN int Primary key,
	Titulo varchar(50),
	Numero int,
	Coleccion varchar(50)
);

CREATE TABLE Autor(
	ID int Primary key,
	Nombre varchar(50),
	Apellido varchar(50),
	Nacionalidad varchar(50)
);

CREATE TABLE Realiza(
	Tebeo int,
	Autor int,
	FOREIGN KEY (Tebeo) REFERENCES Tebeo(ISBN),
	FOREIGN KEY (Autor) REFERENCES Autor(ID),
	Primary key(Tebeo, Autor)
);

INSERT INTO Tebeo values (1, 'El Sulfato Atomico', 23, 'Mortadelo y Filemon'),
						 (2, 'La Tergiversicina', 48, 'Mortadelo y Filemon'),
                         (3, 'La Mina del Aleman Perdido', 8, 'Blueberry'),
                         (4, 'Solanin', 1, 'Solanin');
INSERT INTO Autor values (1, 'Francisco', 'Ibañez', 'España'),
						 (2, 'Jean', 'Giraud', 'Francia'),
                         (3, 'Jean-Michel', 'Charlier', 'Francia'),
                         (4, 'Inio', 'Asano', 'Japon');
INSERT INTO Realiza values(1,1),
						  (2,1),
                          (3,2),
                          (3,3),
                          (4,4);