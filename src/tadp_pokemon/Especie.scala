package tadp_pokemon

case class Especie(
    val incrementoEnergiaMaxima: Int = 1,
    val incrementoPeso: Int = 1,
    val incrementoFuerza: Int = 1,
    val incrementoVelocidad: Int = 1,
    val resistenciaEvolutiva: Int,
    val pesoMaximo: Int = 1000,
    val tipoPrincipal: TipoPokemon,
    val tipoSecundario: Option[TipoPokemon] = None,
    val especieCualEvoluciona: Option[Especie] = None,
    val criterioEvolucion: CriterioEvolucion
    ){
  
  
  def tipoPrincipal(unTipoPrincipal: TipoPokemon): Especie = copy(tipoPrincipal = unTipoPrincipal)
  
  def tipoSecundario(unTipoSecundario: TipoPokemon): Especie = copy(tipoPrincipal = unTipoSecundario)
  
  def esAfin(ataqueBase: AtaqueBase):Boolean = 
    List(tipoPrincipal,Normal).exists(ataqueBase.esDeTipo(_))|| tipoSecundario.exists(ataqueBase.esDeTipo(_))
    
  def pierdeContra(tipo: TipoPokemon) = 
    tipo.leGanaA(tipoPrincipal) || tipoSecundario.exists(tipo.leGanaA(_))
    
  def subirNivel(pokemon: Pokemon, tareaIntermedia: Pokemon => Pokemon):Pokemon = 
    if (pokemon.puedeSubirNivel())
      tareaIntermedia(pokemon.incrementarNivel()).aumentarCaracteristicas()
    else 
      pokemon
    
  def aumentarCaracteristicasPokemon(pokemon: Pokemon): Pokemon =
    pokemon.aumentarCaracteristicas(incrementoEnergiaMaxima, incrementoPeso, incrementoFuerza, incrementoVelocidad)
      
  def experienciaNecesariaProximoNivel(unNivel: Int): Int = 
    if (unNivel >= 1) (2 * experienciaNecesariaProximoNivel(unNivel - 1)) + resistenciaEvolutiva else 0
      
  def hacerEvolucionar(pokemon: Pokemon): Pokemon =
    pokemon.especie(especieCualEvoluciona.getOrElse(this))
    
  def debeEvolucionarTras(actividad: Actividad,pokemon: Pokemon): Boolean =
    criterioEvolucion.debeEvolucionarTras(pokemon,actividad)
  
  def efectosPosterioresActividad(actividad: Actividad, pokemon: Pokemon): Pokemon =    
    subirNivel(pokemon, evolucionarSiDebeTras(actividad,_))
    
  def evolucionarSiDebeTras(actividad: Actividad, pokemon: Pokemon): Pokemon =
    if(debeEvolucionarTras(actividad,pokemon)) hacerEvolucionar(pokemon) else pokemon
    
  def esPrincipalmenteDe(tipo: TipoPokemon): Boolean =
      tipoPrincipal == tipo
      
}