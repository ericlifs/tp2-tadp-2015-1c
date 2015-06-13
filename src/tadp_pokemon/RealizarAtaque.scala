package tadp_pokemon

class RealizarAtaque extends Actividad {
  
  var ataque: Ataque = _
  
  def ataque (unAtaque: Ataque) {
    ataque = unAtaque
  }
  
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
    var unAtaquePokemon = unPokemon.ataques.find((unAtaque: (Ataque, Int, Int)) => unAtaque._1 == ataque)
    
    unAtaquePokemon.map{ ataquePokemon =>
      var puntosAtaquePokemon = ataquePokemon._2
      
      if (puntosAtaquePokemon > 0) {
        var experienciaAGanar = 0
        
        unPokemon.ataques = unPokemon.ataques.map { unAtaque =>
          if(unAtaque._1 == ataque)
            decrementarPA(unAtaque)
          else
            unAtaque
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
  
  def decrementarPA (unAtaque: (Ataque, Int, Int)) : (Ataque, Int, Int) = {
    (unAtaque._1, unAtaque._2 -1, unAtaque._3)
  }
}