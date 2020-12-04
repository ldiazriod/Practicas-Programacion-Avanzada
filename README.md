# Practicas-Programacion-Avanzada

## Contenido añadido:

- Se han añadido dos nuevos metodos en la inferfaz:
1. Vector<String> guardarInfoDatostxt(String path); Este metodo nos permite guardar en un vector todos los datos de un fichero de texto. Con esto podemos añadir contenido a ficheros ya creados.
2. boolean borrarFicheros(File directorio); Este metodo es un algoritmo recursivo que nos permite borrar todos los ficheros del programa.
  
- Se ha añadido una nueva clase Administrador. Con esta clase podemos borrar todos los ficheros del programa. Para ello se necesita una clave que el administrador debe añadir, no es la misma contraseña que la del usuario admin. La contraseña va encriptada con la clase RSA. Además 

- Se ha añadido una nueva clase RSA. Esta clase nos permite hacer varias cosas relacionadas con la encriptación de los datos.



## ¿Por qué utilizar interfaces?:

  Se ha decido meter estos algoritmos en una interfaz para que puedan usarse más adelante en el caso de que los necesitemos para otras clases. Gracias a esto podemos ahorrar tiempo y coste.
  Por otro lado, más adelante nos será más fácil mejorar el código de formas más eficientes y manteniendo las clases lo más limpias posibles.
  
  La idea principal es ir aplicando estas interfaces al resto del código para ir mejorando la aplicación.
  
## ¡Estamos trabajando en ello!:

En esto es en lo que se está trabajando y estará en futuras versiones

- Meter un método de confirmación de los datos del usuario al crear una sesión. Con este metodo el usuario podrá cambiar los datos en el caso de que se equivoque al crear una cuenta.
- Crear un método que te permita cancelar una reserva.
- Crear un método que te permita borrar un usuario.
- Cambiar el programa para que tenga herencia.
- Crear un método que te calcule el precio total de la reserva según los dias que te quedes en el hotel.

## Testeo

Creacion de una nueva cuenta con licencia de Hotelero:

![](Assets/CrearCuentaHotelero.png)

La cuenta se crea correctamente junto con la carpeta:


