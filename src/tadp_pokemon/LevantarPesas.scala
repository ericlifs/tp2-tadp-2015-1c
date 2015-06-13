package tadp_pokemon

object LevantarPesas extends Actividad {
  
  var pesoLevantado: Int = 0
  
  def pesoLevantado (unPesoLevantado: Int) {
    pesoLevantado = unPesoLevantado
  }
  
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
    
    var factorPesoLevantado = 1
    
    unPokemon.especie.tipoPrincipal match {
      case Fantasma => 
      case tipo: TipoPokemon => {
        if (tipo == Pelea || unPokemon.especie.tipoPrincipal == Pelea)
          factorPesoLevantado = 2
      }
    }
    
    if (unPokemon.estado == Paralizado)
      unPokemon.estado = KnockOut
      
    var experienciaAGanar = pesoLevantado * factorPesoLevantado
    if ((unPokemon.fuerza * 10) > experienciaAGanar)
      unPokemon.estado(Paralizado)
    else
      unPokemon.experiencia(unPokemon.experiencia + experienciaAGanar)
    
    unPokemon
  }
}
