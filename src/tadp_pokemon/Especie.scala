package tadp_pokemon

case class Especie(
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
  
  
  def tipoPrincipal(unTipoPrincipal: TipoPokemon): Especie = copy(tipoPrincipal = unTipoPrincipal)
  
  def tipoSecundario(unTipoSecundario: TipoPokemon): Especie = copy(tipoPrincipal = unTipoSecundario)
  
  def esAfin(ataqueBase: AtaqueBase):Boolean = 
    List(tipoPrincipal,Normal).exists(ataqueBase.esDeTipo(_))|| tipoSecundario.exists(ataqueBase.esDeTipo(_))
    
  def pierdeContra(tipo: TipoPokemon) = 
    tipo.leGanaA(tipoPrincipal) || tipoSecundario.exists(tipo.leGanaA(_))
    
  def subirNivel(pokemon: Pokemon, tareaIntermedia: Pokemon => Pokemon):Pokemon = 
    if (pokemon.puedeEvolucionar())
      tareaIntermedia(pokemon.incrementarNivel()).aumentarCaracteristicas(incrementoEnergiaMaxima, incrementoPeso, incrementoFuerza, incrementoVelocidad)
    else 
      pokemon
    
  def experienciaNecesariaProximoNivel(unNivel: Int): Int = 
    if (unNivel >= 1) (2 * experienciaNecesariaProximoNivel(unNivel - 1)) + resistenciaEvolutiva else 0
      
  def hacerEvolucionar(pokemon: Pokemon): Pokemon =
    pokemon.especie(especieCualEvoluciona.getOrElse(this))
    
  def debeEvolucionarTras(actividad: Actividad,pokemon: Pokemon): Boolean =
    criterioEvolucion.debeEvolucionarTras(pokemon,actividad)
  
  def efectosPosterioresActividad(actividad: Actividad, pokemon: Pokemon): Pokemon =    
    subirNivel(pokemon, if(debeEvolucionarTras(actividad,pokemon)) hacerEvolucionar(_) else identity(_))
    
  def esPrincipalmenteDe(tipo: TipoPokemon): Boolean =
      tipoPrincipal == tipo
      
}