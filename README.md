#Gimnasio Pokemon - Decisiones

* Se decidio hacer un Option para modelar la posibilidad de evolucionar el cual contiene tanto el criteri como la especie a la cual se evoluciona
* Se decidio modelar con un option los tipos secudnarios que pueden estar o no
* Para modelar la posibilidad de que una actividad no pueda ser hecha, decidio Utilizarse un Try. Esto permite que en las rutinas se ejecuten varias actividades en un pipeline sin interrumpir el flujo
* Se decidio que las actividades, al ser la parte potencialmente mas variable del sistema y estar siemrpe necesitando datos y generando "efectos" sobre el pokemon, contengan el codigo que las resuelve incluso si este necesita romper el encapsulamiento del pokemon. De no ser así, probablemente habría varios casos molestos de double(o multiple)-dispatch y además el pokemon tendria mucho código necesario solo para las actividades, acoplándose a estas. En su lugar, prefirió verse al pokemon como una estructura y a las actividades como operaciones que actúan sobre ella.
* Para aminorar levemente el conocimiento que tienen las actividades del pokemon, se decidio definir un metodo unapply custom que permite que el pokemon "revele" su estructura interna para ser usada por la actividad, pero ocultando ciertos detalles internos de cómo se compone esta estructura.
* Se decidió que las evoluciones funcionaran con un esquema tal que tras toda actividad el sistema verifica si el pokemon debe evolucionar o no y en caso de que sí, lo hace, ya que la evolución puede ocurrir a raíz de muchas actividades diferentes y no uniformes.
* Se decidió que para el análisis de rutinas el usuario pueda pasar una función custom que dice cuando un pokemon es mejor que otro.
* Se decidio hacer inmutable al pokemon para poder realizar simulaciones sobre el sin perder el original.
* Se decidio dejar como mytables a los ataques porque originalmente fueron hechos así y su mutabilidad no perjudicaba a la solución, incluso hacía algunas sentencias mas simples. Sin embargo en la solución en general tendió a favorecerse la inmutabilidad de los objetos.
* En varios casos se aprovecharon herramientas funcionales (Ej: orden superior en varios filters/maps, uso de mónadas, aplicación parcial al decidir la especie qué hacer luego de increpemtar el nivel, etc.)
* Dado que los estados usualmente están bastante acoplados al objeto y que suelen realizar oepraciones sobre estos, se decidio utilizar polimorfismo paramétrico para estos casos también.
* Para el uso que las especies hacen de los criterios de evolución, se utilizó polimorfismo paramétrico ya que permitía desacoplar estos objetos y no generaba idas y vueltas de mensajes.
* También se decidió hacer algunos métodos en el pokemón con el fin de no acoplar los criterios de evolucón a éste.
* Se decidió modelar con un option el caso de que ninguna rutina sea posible para el pokemon
* Se combinó polimorfismo parametrico con los Ataques cuando era necesario tomar decisiones sobre su estructura (dado que estas siempre afectaban a otro objeto, por lo cual generarían multiple-dispatches) y polimorfismo ad-hoc en otros casos, como a la hora de que realizaran su efecto colateral.
* Se definio una RutinaVacia como neutro de la comparación entre rutinas dado que una rutina con la lista vacía podía ser dada por el usuario (x ej para ver si siempre qedaría peor de lo que está) y necesitaba hacerse la distinción.
* Se contempló jerarquizar de mejor forma a las actividades según si estas tienen o no validaciones, en lugarde inicializar el metodo que valida en true y refefiniendolo cuando hace falta. Sin embargo esto no se llevó a cabo por simplicidad.
