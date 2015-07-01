package tadp_pokemon

object LevantarPesas extends Actividad {
  
  var pesoLevantado: Int = 0
  
  def pesoLevantado (unPesoLevantado: Int) {
    pesoLevantado = unPesoLevantado
  }
  
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
    
    var factorPesoLevantado = 1
    var nuevoPokemon = new Pokemon()
    
    unPokemon.especie.tipoPrincipal match {
      case Fantasma => 
      case tipo: TipoPokemon => {
        if (tipo == Pelea || unPokemon.especie.tipoPrincipal == Pelea)
          factorPesoLevantado = 2
      }
    }
    
    if (unPokemon.estado == Paralizado)
      nuevoPokemon = unPokemon.copy(estado = KnockOut);
      
    var experienciaAGanar = pesoLevantado * factorPesoLevantado
    if ((nuevoPokemon.fuerza * 10) > experienciaAGanar)
      nuevoPokemon.estado(Paralizado)
    else
      nuevoPokemon.experiencia(unPokemon.experiencia + experienciaAGanar)
    
    nuevoPokemon
  }
}
