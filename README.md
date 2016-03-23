# EngineeringMathTool
A mathematical library written in java

Los nombres de clases e interfaces estaran en Español o Spanglish para evitar usar nombres de clases ya existentes.

A raiz de estar desarrollando la libreria en java creo que me he dado cuenta de la cantidad de parches que tiene. 
Se nota por ejemplo en el hecho de que un array de bits que deberia ocupar us solo bit por cada elemento y una cabecera por ser un array en realidad tiene una cabecera por cada elemento, cosa bastante ridicula. Para solucionarlo desde la propia documentacion de oracle aconsejan usar BitSet que no es otra cosa que una clase que almacena el vector de bits en forma de un entero y utilizando cada posición dentro de la representacion en forma de array de bits como un elemento.
Luego ya el hecho de no soportar sobrecarga de operadores pero que ciertas librerias como la clase String si que puedan ya es el colmo.
