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

## Matrices
Las matrices ocupan una posición muy importante, pues practicamente todas las carreras de ingenieria las usan. Desde circuitos lógicos hasta tratamiento digital de señal.
Se permitiran operaciones como 
```
+,-, * , / , % , ! ,.* ,./,.%,.!... 
```
El simbolo "!" es el de factorial (Solo permitido si es entero) y las operaciones con un punto ".*" denotan 
operación punto por punto en las que las operaciones se realizan elemento a elemento.
* " * ": [1,2] * [2,3;2,3]=[6,9]
* ". * ":[1,2].* [2,3]=[2,6]
