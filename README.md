# RMIAgendaDistribuida

Este proyecto demuestra una arquitectura distribuida en Java RMI donde un cliente puede conectarse a un servidor remoto que realiza operaciones CRUD sobre una base de datos MySQL.

## Estructura del Proyecto

- interfaces/IAgenda.java → interfaz remota.
- servidor/AgendaImpl.java → implementación de la lógica de negocio.
- servidor/Servidor.java → registro RMI.
- servidor/DBUtil.java → utilidades de conexión MySQL.
- cliente/Cliente.java → aplicación cliente.
- sql/crear_agenda.sql → script de base de datos.

## Requisitos

- Java JDK 11+
- MySQL Server (solo en VM servidor)
- Red entre las máquinas (puerto 1099 abierto)

## Instrucciones

1. En VM1 (Servidor):
   - Configura MySQL.
   - Ejecuta Servidor.java

2. En VM2 (Cliente):
   - Edita Cliente.java con la IP del servidor.
   - Ejecuta Cliente.java
