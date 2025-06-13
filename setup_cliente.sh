#!/bin/bash

echo "🧰 Configuración automática del cliente Java RMI"

if [[ "$EUID" -eq 0 ]]; then
echo "⚠️ Por seguridad, no ejecutes este script como root. Usa un usuario normal."
exit 1
fi

# echo "📦 Verificando instalación de Java..."
# sudo apt update
# sudo apt install -y default-jdk

echo "🔨 Compilando clases cliente..."
mkdir -p cliente/bin
javac -d cliente/bin interfaces/*.java cliente/*.java

echo "🚀 Ejecutando cliente..."
cd cliente/bin
java cliente.Cliente

