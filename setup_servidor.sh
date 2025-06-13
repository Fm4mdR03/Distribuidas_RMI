#!/bin/bash

echo "🚀 Configuración automática del servidor RMI - Distribuidas"

if [[ "$EUID" -ne 0 ]]; then
echo "❌ Este script debe ejecutarse como root. Usa: sudo ./setup_servidor.sh"
exit 1
fi

JDBC_VERSION="8.4.0"
JDBC_URL="https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-${JDBC_VERSION}.tar.gz"
JDBC_DIR="mysql-connector-j-${JDBC_VERSION}"
JDBC_JAR="mysql-connector-j-${JDBC_VERSION}.jar"
PROJECT_DIR="$PWD"
IP_SERVIDOR=$(hostname -I | awk '{print $1}')
CLASSPATH="$PROJECT_DIR/$JDBC_DIR/$JDBC_JAR:."

echo "📡 IP del servidor detectada: $IP_SERVIDOR"

# echo "📦 Instalando Java y MySQL..."
# apt update && apt install -y default-jdk mysql-server wget

# if [[ ! -f "$JDBC_DIR/$JDBC_JAR" ]]; then
# echo "⬇️ Descargando MySQL JDBC Connector..."
# wget $JDBC_URL
# tar -xzf mysql-connector-j-${JDBC_VERSION}.tar.gz
# else
# echo "✅ Driver JDBC ya existe."
# fi

echo "🛠️ Configurando base de datos..."

mysql -u root <<EOF
CREATE DATABASE IF NOT EXISTS agenda_db;
CREATE USER IF NOT EXISTS 'Usuario_Distribuidas'@'%' IDENTIFIED BY '12345678';
GRANT ALL PRIVILEGES ON agenda_db.* TO 'Usuario_Distribuidas'@'%';
FLUSH PRIVILEGES;
USE agenda_db;
CREATE TABLE IF NOT EXISTS contactos (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
telefono VARCHAR(20) NOT NULL
);
EOF

echo "✅ Base de datos y usuario configurados."

echo "🧱 Compilando clases Java..."

mkdir -p servidor/bin
javac -cp "$CLASSPATH" -d servidor/bin interfaces/*.java servidor/*.java

echo "🚀 Iniciando servidor RMI..."

cd servidor/bin
java -cp "$CLASSPATH" -Djava.rmi.server.hostname=$IP_SERVIDOR servidor.Servidor