# Ejemplo de Servicio REST

Para proteger el servicio REST usando el Token JWT, este servicio debe procesar solamente llamadas  que contengan un token JWT válido, esto implica: 

 * Comunciarse con el Servidor de Autorización (en este caso Keycloak) que emitió el token y confimar que la firma eletrónica del JWT es válida.
* Verificar que el JWT no ha expirado
* Verifica que el JWT fue creado para consumir este servicio (audiencia)
* Veririficar el JWT contiene el rol necesario para consumir la ruta deseada. 

Estas verificaciones las realiza de forma automática el [adaptador de aplicación cliente de Keycloak](https://www.keycloak.org/downloads.html) 

Este es un ejemplo de servicio REST usando el adaptador de Keycloak para SpringBoot.

**Adaptador Keycloak**:  Se encarga de controlar la interacción de la aplicación Springboot con el servidor Keycloak para verficar los token de acceso JWT. También permite definir la reglas de autorización de rutas en base a los roles contenidos en el JWT.  La configuración de este adaptador esta disponible en el archivo de [propiedades de la aplicación](/apirest/src/main/resources/application.properties). 



## Instalación y uso
Descargue el contenido de esta carpeta, asegurese de tener instalado Maven y Java.

```
~# mvn clean install
~# java -jar target/apirest-0.1.jar
```
En este punto el servicio se está ejecutando y esta listo para ser consumido desde algún cliente usando un JWT de acceso.

```
~# curl -H "Authorization: Bearer TOKEN_JWT_VALIDO" http://1.2.3.4:8080/saludar?nombre=Jose

{"id":1,"content":"Hola, Jose!"}

~# curl -H "Authorization: Bearer TOKEN_JWT_INVALIDO" http://127.0.0.1:8080/saludar?nombre=Jose

{"status":401,"error":"Unauthorized","message":"","path":"/saludar"}

```
Un Token JWT es inválido cuando ha expirado o cuando no haya sido emitido por el servidor Keycloak, es decir su firma electrónica no coincide con la firma servidor Keycloak. 

El administrador de Reino en Keycloak establece el tiempo de vida de los JWT (5 minutos por defecto).