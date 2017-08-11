alter user dxcorzo set timezone = 'Etc/GMT+5';

CREATE TABLE Departamentos
(
  Id serial not null primary key,
  Nombre varchar not null,
  Estado bit not null
);

CREATE TABLE Ciudades
(
  Id serial not null primary key,
  Nombre varchar not null,
  IdDepartamento int not null references Departamentos(Id),
  Estado bit not null
);

CREATE TABLE Humedades
(
  Id serial not null primary key,
  Valor varchar not null,
  Fecha timestamp not null default CURRENT_TIMESTAMP,
  IdCiudad int not null references Ciudades(Id),
  Estado boolean not null default true
);
