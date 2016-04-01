# Sintaxis
## Funciones
Para definir una función seguiremos la notación utilizada por MATLAB y OCTAVE pues es intuitiva y permite una buena forma de devolver varios valores sin tener que usar la palabra "return".
```
function [a,b] = miFuncion1(param1,param2,param3)
  //Los comentarios igual que en java, pues son rapidos y "%" esta reservado para la operacion resto.
  ...
end
```
Si en los parametros de entrada hacemos param1=3, le estaremos diciendo al programa que el param1 por defecto vale 3 si no le hemos dado valor.

```
function [a,b] = miFuncion1(param1=3,param2,param3)
  ...
end
```
Así podremos llamar a la funcion sin pasar el primer parametro pero si el segundo y tercero:
```
miFuncion1(,2,3);
```
Las funciones internamente tienen su propio contexto, de esta forma no pueden acceder a las variables del área de trabajo pero si a las funciones que están cargadas en el área de trabajo.
Los áreas de trabajo son en el fondo carpetas con los scripts y archivos de funciones, pero si es necesario importar alfuna función sin que copiemos los archivos se usará un archivo llamado ".immports" que incluira los paths de las funciones.
Una limitación encontrada en MATLAB que me gustaria poder superar es el hecho de que cada archivo solo pueda tener una funcion y deba tener el mismo nombre que el fichero asi como la necesidad de tener que copiar los archivos que utilices en el mismo path añadiendo el problema de los cambios de version entre archivos.
Esta limitación se resolvera con el archivo ".includes" y una carpeta donde cargar funciones muy usadas y que se cargarán directamente en el contexto.

### Trabajo con Matrices
Para trabajr con matrices se utilizarán los símbolos "[" y "]". Asi para crear una matriz en cualquier punto:
```
[1,2,3;4,5,6;7,8,9]
Crearia:
[1 2 3
 4 5 6
 7 8 9]
```
Para acceder a cualquier valor de una matriz:
```
matriz[1,2] : Accederia al valor en la segunda fila (se empieza contando desde el 0) y la tercera columna.
matriz[1:2,1:3] : Devolveria una matriz de dimension 2x3 obteniendo los valores de la matriz.
Asi cogeriamos los elementos de la matriz de la segunda y tercer fila y de las columnas 2 hasta la 4.
matriz[1:1:2,1:2:3]: Haria parecido al anterior código pero nos devolveria una matriz 2x2. Cogeriamos los elementos de la segunda y tercer fila y los elementos de la 2 y 4 fila (El valor intermedio: 1:"2":3 indica un salto entre filas o columnas).
```

Se permitiran operaciones como 
```
+,-, * , / , % , ! ,.* ,./,.%,.!... 
```
El simbolo "!" es el de factorial (Solo permitido si es entero) y las operaciones con un punto ".*" denotan 
operación punto por punto en las que las operaciones se realizan elemento a elemento.
* " * ": [1,2] * [2,3;2,3]=[6,9]
* ". * ":[1,2].* [2,3]=[2,6]
