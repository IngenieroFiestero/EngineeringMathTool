# EngineeringMathTool
A mathematical library written in java
## Primera aproximacion sobre la Sintaxis
La idea es poder permitir sencillez y eliminar la mayor cantidad de parentesis posible para facilitar la visualización del codigo asi como facilitar la escritura. Ej: .3 que es lo mismo que 0.3
* 7-2(5-6) =>Transformacion => 7-2*(5-6)
* (3-5)7-(1+2) => Transformacion => (3-5)*7-(2+3)
* (12-4)y-1 => Transformacion => (12-4)*y - 1
* 3x+1 => Transformacion => 3*x + 1
* x21+4 : Si x21 is variable? sino lo es Se lanza error de variable x21 no encontrada
* (4-5)(2 * x) => Transformacion => (4-5) * (2*x)
* (-.3 + 7) => Transformacion => (-0.3 + 7)
* 3 * (.5+4) => Transformacion => 3 * (0.5+4)
* (4+5).7-2 => Transformacion =>  (4+5)*0.7 -2
* 5 .* 9+1 => Valido Puesto que " .* " es una operacion de multiplicación punto por punto para vectores. Ej: [2,3].*[2,3]=[4,9]
* (3+2.)*5 => Transformacion => (3+2)*5
* '3'*2 => ValorAscii(3) * 2
* 1^2 +2^2 +3^2 => Transformacion siempre que haya espacios en blanco=> 1^(2)+2^(2)+3^(2)

Los nombres de clases e interfaces estaran en Español o Spanglish para evitar usar nombres de clases ya existentes.
Estoy todavia rehaciendo la clase ValorNumerico que pasará a trabajar directamente con complejos y no a ser un contenedor de otros números.
La libreria Trabajara directamente consigomisma para los calculos sencillos y para los más complejos se utilizara la libreria common-math de apache que por lo que he visto parece muy avanzada y sigue siendo actualizada.

A raiz de estar desarrollando la libreria en java creo que me he dado cuenta de la cantidad de parches que tiene. 
Se nota por ejemplo en el hecho de que un array de bits que deberia ocupar us solo bit por cada elemento y una cabecera por ser un array en realidad tiene una cabecera por cada elemento, cosa bastante ridicula. Para solucionarlo desde la propia documentacion de oracle aconsejan usar BitSet que no es otra cosa que una clase que almacena el vector de bits en forma de un entero y utilizando cada posición dentro de la representacion en forma de array de bits como un elemento.
Luego ya el hecho de no soportar sobrecarga de operadores pero que ciertas librerias como la clase String si que puedan ya es el colmo.
