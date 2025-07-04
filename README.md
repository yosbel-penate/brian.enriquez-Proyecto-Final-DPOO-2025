# Documento de Visión Inicial

## Índice

- [**Integrantes**](#integrantes)
- [**Tema**](#tema)
- [**Desarrollo**](#desarrollo)
- [**Tareas**](#tareas)
- [**Clases**](#clases)
- [**Tecnologías y Librerías**](#tecnologías-y-librerías)
- [**Funciones**](#funciones)
- [**Estructura**](#estructura)

## Integrantes
- **Guillermo León Rodríguez**
- **Brian Ramírez Enríquez**
- **Dassiel Ernesto Quintero Rubido**

## Tema

``Lechugas del Caos: El Jardín de los Enigmas``

## Desarrollo

>Basándonos en el desarrollo incremental, o sea, construir el programa en pequeñas partes hasta obtener la versión completa, crearemos un juego simple y le iremos añadiendo mecánicas y funciones hasta obtener el resultado deseado.

## Tareas


- ``Guillermo``: Creación de los bonos y su implementación y lops puzzles.
- ``Brian``: Diseño y creación de héroes, y elementos útiles para el desarrollo
- ``Dasiel``: Creación de los sprites, los enemigos y las clases visuales.

## Clases

- ``Main``: Añade los elementos (personajes, mapas, etc.) a la ventana del juego y lanza esta última.
- ``Character``: Usada para crear un personaje con sus distintas características.
- ``Hero``: Extiende de Character y añade los atributos específicos de un héroe.
- ``Enemy``: Extiende de Character y añade los atributos específicos de un enemigo.
- ``WorldMap``: Crea el mapa del mundo.
- ``Options``: Crea el menú de opciones del juego.
- ``MainMenu``: Crea el menu de inicio del juego que permite elegir el nivel e iniciar el juego.

## Tecnologías y Librerías
java swing
## Funciones

- Movimiento en 4 direcciones (arriba, abajo, derecha e izquierda).
- Multijugador con hasta 5 jugadores simultáneos.
- Varios personajes  y enemigos con habilidades y ataques únicos.
- Enigmas en cada mundo.
- Movimientos por turno.
- Gráficos 2D, con barra de habilidades, minimapa y animaciones.

## Estructura

``` plaintext
LechugasDelCaos/
│── src/
│   ├─ main/
│      ├── java/
│      │   ├── com/lechugasdelcaos/
│      │       ├── app/  
│      │       ├── controllers/
│      │       ├── models/       
│      │       ├── views/
│      ├── resources/
│── build.gradle
│── README.md
│── .gitignore
```

- ``app``: Punto de entrada del juego.
- ``controllers``: Lógica de la UI y el juego.
- ``models``: Clases del juego (personajes, enemigos, tablero).
- ``views``: Renderización de la UI.
- ``resources``: Recursos gráficos.
- ``build.gradle``: Configuración de gradle.
- ``README.md``: Descripción del proyecto.
- ``.gitignore``: Archivos a excluir en Git.

                          Requisitos:
Requisitos del sistema:
Java JDK 11 o superior
SO: Windows
Memoria RAM :2GB
Procesador Intel Celeron D 351
                      Diseño del Juego:
Advertencia: Esta es una versión inicial del juego y como versión inicial al fin, entiéndase que este apartado puede cambiar radicalmente durante el desarrollo del proyecto.
El juego contará con 4 clases principales, las cuales serán:
•	Clase Game: será la principal clase controladora y se encargará de controlar todos los datos del software.
•	Clase Map: será la encargada de guardar y generar todos los datos de los mapas o niveles.
•	Clase Element: Esta se encargará de la modelación de varios aspectos indispensables para el juego, tales como: potenciadores, obstáculos, los puzles o los personajes.
•	Clase Generadora: esta clase esta pensada para ser la encargada de generar todos los objetos de las clases anteriores, principalmente los de la clase Element.
La herencia y el polimorfismo se harán presentes sobre todo en la clase Element, ya que esta heredará de clases inferiores varios objetos, métodos y atributos que serán necesarios para el funcionamiento del software, los polimorfismos aún no han sido decididos.
Habrán relaciones entre clases de menor nivel por ejemplo:
La clase Position tendrá una relación de composición con la clase Element.

