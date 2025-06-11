# Distribuidas_RMI

1. sudo apt update && sudo apt install default-jdk
2. java -version

## Abrir puertos
3. sudo ufw allow 1099
4. sudo ufw enable

## IPs
* Cliente:
    - 192.168.100.1
* Servidor:
    - 192.168.100.2

## Estructura
- IHello.java: Interfaz remota.
- HelloImpl.java: Implementación de la interfaz remota.
- Servidor.java: Clase que inicia el servidor RMI.
- Cliente.java: Clase que actúa como cliente RMI.

## Ejecución
En el servidor:
javac *.java
java Servidor

En el cliente:
javac *.java
java Cliente