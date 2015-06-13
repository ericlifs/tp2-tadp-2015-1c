package tadp_pokemon

object RealizarAtaque extends Actividad {
  
  var ataque: Ataque = _
  
  def ataque (unAtaque: Ataque) {
    ataque = unAtaque
  }
  
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
    var ataquePokemon = unPokemon.ataques.find((ataque: (Ataque, Int, Int)) => ataque._1 == ataque)
    
    if (ataquePokemon != None) {
      var puntosAtaquePokemon = ataquePokemon.get._2
      
      if (puntosAtaquePokemon > 0) {
        var experienciaAGanar = 0
        
        unPokemon.ataques = unPokemon.ataques.map{ ataquePokemon => 
          if (ataquePokemon._1 == ataque)
            (ataquePokemon._1, ataquePokemon._2 - 1, ataquePokemon._3)
          else
            ataquePokemon
        }
        
        ataque.tipo match {
          case Dragon => experienciaAGanar = 80
          case tipoAtaque if tipoAtaque == unPokemon.especie.tipoPrincipal => experienciaAGanar = 50
          case tipoAtaque if tipoAtaque == unPokemon.especie.tipoSecundario => {
            unPokemon.genero match {
              case Macho => experienciaAGanar = 20
              case _ => experienciaAGanar = 40
            }
          }
        }
        
        unPokemon.experiencia(unPokemon.experiencia + experienciaAGanar)
        
        if (ataque.efectoColateral != null)
          ataque.efectoColateral.efecto(unPokemon)
      }
    
    }
    
    unPokemon
  }
}