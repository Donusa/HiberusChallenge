# HiberusChallenge - Instructivo de uso
# Proyecto Fullstack Angular + Spring

Este repositorio contiene una aplicación fullstack desarrollada con Angular y Spring. 
Asegúrate de seguir las instrucciones a continuación para configurar y ejecutar el proyecto correctamente.

## Requisitos Previos

- Clonar el repositorio
- Docker (instalado, abierto y corriendo)

## Configuración

1. **Completar las credenciales**:
   Abre el archivo `docker-compose.yml` y completa los siguientes valores:
   ```yaml
   services:
     hiberus:
       environment:
         DB_PASSWORD: # Completá con la contraseña de la bdd
         API_KEY: #Completá con la api key que recibiste por mail
       environment:
         MYSQL_ROOT_PASSWORD: # Completá con la contraseña de root
         MYSQL_PASSWORD: # Completá con la contraseña

## Ejecucion

1. Abre la consola de comandos en el directorio que se encuentro el archivo `docker-compose.yml`
2. Ejecuta el comando `docker-compose up --build`

## Aplicación lista

Si se ejecutaron los pasos anteriores correctamente deberian poder acceder a la aplicaciona  través de localhost:4200
Si se desea realizar alguna consulta directamente por postman será a traves de localhost:8080
