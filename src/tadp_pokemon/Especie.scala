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
    val especieCualEvoluciona: Option[Especie] = None,
    val criterioEvolucion: CriterioEvolucion
    ){
  
  def esAfin(ataqueBase: AtaqueBase):Boolean = 
    List(tipoPrincipal,Normal).exists(ataqueBase.esDeTipo(_))|| tipoSecundario.exists(ataqueBase.esDeTipo(_))
    
  def pierdeContra(tipo: TipoPokemon) = 
    tipo.leGanaA(tipoPrincipal) || tipoSecundario.exists(tipo.leGanaA(_))
    
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
    
  def debeEvolucionarTras(actividad: Actividad,pokemon: Pokemon): Boolean =
    criterioEvolucion.debeEvolucionarTras(pokemon,actividad)
  
  def evolucionarSiDebeTras(actividad: Actividad,pokemon: Pokemon):Pokemon =
    if(debeEvolucionarTras(actividad,pokemon)) hacerEvolucionar(pokemon) else pokemon
    
  def esPrincipalmenteDe(tipo: TipoPokemon): Boolean =
      tipoPrincipal == tipo
      
}