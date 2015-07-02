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
    val especieCualEvoluciona: Option[Especie],            //TODO hacer option e inicializar en None;  
    val criterioEvolucion: CriterioEvolucion
    ){
  
  def esAfin(ataque: Ataque):Boolean = 
    List(tipoPrincipal,Normal).exists(ataque.esDeTipo(_))|| tipoSecundario.forall(ataque.esDeTipo(_))

  def nuevoNivel(pokemon: Pokemon, experiencia: Int):Pokemon = 
    if (experiencia >= experienciaNecesariaProximoNivel(pokemon.nivel)) aumentarCaracteristicas(pokemon) else pokemon

  def experienciaNecesariaProximoNivel(unNivel: Int): Int = 
    if (unNivel >= 1) (2 * experienciaNecesariaProximoNivel(unNivel - 1)) + resistenciaEvolutiva else 0

  def aumentarCaracteristicas(pokemon:Pokemon): Pokemon = 
    pokemon.aumentarEnergiaMaxima(incrementoEnergiaMaxima)
    .aumentarPeso(incrementoPeso)
    .aumentarFuerza(incrementoFuerza)
    .aumentarVelocidad(incrementoVelocidad)
    
  def hacerEvolucionar(pokemon: Pokemon): Pokemon =
    pokemon.especie(especieCualEvoluciona.getOrElse(this))

  
}