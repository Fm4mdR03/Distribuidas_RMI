#!/bin/bash

echo "🧰 Configuración automática del cliente Java RMI"

if [[ "$EUID" -eq 0 ]]; then
echo "⚠️ Por seguridad, no ejecutes este script como root. Usa un usuario normal."
exit 1
fi

echo "📦 Verificando instalación de Java..."
sudo apt update
sudo apt install -y default-jdk

read -p "📡 Ingrese la IP de la máquina servidor RMI (ej: 192.168.100.10): " IP_SERVIDOR

CLIENTE_JAVA="cliente/Cliente.java"

if grep -q "String host = " "$CLIENTE_JAVA"; then
echo "🛠️ Modificando IP del servidor en Cliente.java"
sed -i "s/String host = ".*";/String host = "$IP_SERVIDOR"/" "$CLIENTE_JAVA"
else
echo "⚠️ No se encontró línea con 'String host = ...' en Cliente.java"
fi

echo "🔨 Compilando clases cliente..."
mkdir -p cliente/bin
javac -d cliente/bin interfaces/*.java cliente/*.java

echo "🚀 Ejecutando cliente..."
cd cliente/bin
java cliente.Cliente

