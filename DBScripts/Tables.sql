alter user dxcorzo set timezone = 'Etc/GMT+5';

CREATE TABLE Departamentos
(
  Id serial not null primary key,
  Nombre varchar not null,
  Estado boolean not null default true
);

CREATE TABLE Ciudades
(
  Id serial not null primary key,
  Nombre varchar not null,
  IdDepartamento int not null references Departamentos(Id),
  Estado boolean not null default true
);

CREATE TABLE Humedades
(
  Id serial not null primary key,
  Valor varchar not null,
  Temperatura varchar not null,
  Fecha timestamp not null default CURRENT_TIMESTAMP,
  IdCiudad int not null references Ciudades(Id),
  Estado boolean not null default true
);

insert into Departamentos (Nombre) values ('Antioquia'),('Cundinamarca'),('Amazonas'),('Santander');

insert into ciudades(IdDepartamento, Nombre) values
(1, 'Medellín'),(1, 'Bello'),(2, 'Bogotá'),(2, 'Villeta'),(2, 'Sopó'),
(3, 'Leticia'),(4, 'San Gil'),(4, 'Málaga'),(4, 'Bucaramanga');
