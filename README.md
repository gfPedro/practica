# Práctica de programación

En esta práctica se ha desarrollado una aplicación básica en la que se pueden dar de alta usuarios y autenticarse con esas credenciales.

También implementa un sencillo listado de los usuarios creados al que se puede acceder si se está autenticado en la aplicación.

## Instalación

### Backend

Una vez clonado el repositorio podremos importar el proyecto Maven 'Practica' en eclipse y con el arrancar nuestro backend que expondrá los endpoints necesarios y arrancará una base de datos en memoria. Por defecto se ha configurado para que sea persistente (los datos se guardarán en un archivo) pero se puede cambiar para que sea volátil descomentando la sección correspondiente del application.properties.

Me habría gustado crear dos application.properties en otro repositorio y configurar la aplicación para que obtenga uno u otro en base a una única variable pero por falta de tiempo no me ha sido posible.

### Frontend

Para arrancar el frontend de nuestra aplicación lo que haremos es ir a la carpeta raíz del proyecto angula 'Practica-frontend' y ejecutar los siguientes comandos:

```bash
npm install
ng serve -o
```

Con esto debería arrancar y abrir una pestaña en nuestro navegador para poder usar la aplicación.


## Funcionamiento

### Login
La aplicación por defecto comenzará con la pantalla de autenticación, la cual si no hemos registrado previamente ningún usuario no nos servirá de mucho.

Por eso podremos acceder a la pantalla de alta de usuarios a través de la botonera situada en la parte superior izquierda.

También podremos intentar acceder al listado de usuarios pero al no estar autenticados no podremos debido a la validación que tienen tanto el frontend como el backend (Bearer token).

### Alta
En la pantalla de alta podremos completar el formulario y dar de alta nuevos usuarios, si alguno de los campos no es correcto veremos un mensaje orientativo al cambiar de campo o al intentar enviar el formulario.

Nuevamente desde esta pantalla dispondremos de la botonera mencionada anteriormente, el único cambio en este caso sería que si estamos autenticados el botón de login cambiaría a logout con su correspondiente lógica y redirección a la pantalla de login.

### Usuarios
En esta pantalla simplemente contamos con una tabla en la que se muestra un listado paginado con los usuarios dados de alta en la aplicación. En ella podremos cambiar de página, si hay más de una, o reordenar el listado haciendo click en la cabecera de la columna por la que se quiere ordenar. Por defecto el listado estará ordenado por nombre.

La botonera en este caso constará siempre del botón de logout que nos lleva a la pantalla de login y el botón de alta que nos llevará al formulario de alta de usuarios.