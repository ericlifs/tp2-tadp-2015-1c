package tadp_pokemon

class Especie(
    val incrementoEnergiaMaxima: Int = 0,
    val incrementoPeso: Int = 0,
    val incrementoFuerza: Int = 0,
    val incrementoVelocidad: Int = 0,
    val resistenciaEvolutiva: Int,
    val pesoMaximo: Int,
    val tipoPrincipal: TipoPokemon,
    val tipoSecundario: Option[TipoPokemon] = None,
    val especieCualEvoluciona: Especie,            //TODO hacer option e inicializar en None;  
    val criterioEvolucion: CriterioEvolucion
    ){
  
  def esAfin(ataque: Ataque):Boolean = 
    List(tipoPrincipal,Normal).exists(ataque.esDeTipo(_))|| tipoSecundario.forall(ataque.esDeTipo(_))
    
}