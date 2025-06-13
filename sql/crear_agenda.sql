-- Crear base de datos
CREATE DATABASE IF NOT EXISTS agenda_db;

-- Crear usuario
CREATE USER IF NOT EXISTS 'Usuario_Distribuidas'@'%' IDENTIFIED BY '12345678';

-- Dar todos los privilegios al usuario sobre la base de datos
GRANT ALL PRIVILEGES ON agenda_db.* TO 'Usuario_Distribuidas'@'%';

-- Aplicar cambios
FLUSH PRIVILEGES;

-- Seleccionar la base de datos
USE agenda_db;

-- Crear tabla de contactos
CREATE TABLE IF NOT EXISTS contactos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);
