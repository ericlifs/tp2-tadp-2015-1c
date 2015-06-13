package tadp_pokemon

object UsarPiedra extends Actividad {
  
  var piedra: PiedraEvolutiva = _
  
  def piedra (unaPiedra: PiedraEvolutiva) {
    piedra = unaPiedra
  }
  
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
    
    unPokemon.especie.criterioEvolucion match {
      case CriterioExpuestoPiedra => {
        if (piedra == PiedraLunar)
          unPokemon.evolucionar()
        else if (piedra.tipo == unPokemon.especie.tipoPrincipal) {
          unPokemon.evolucionar()
        } else if (piedra.tipo.leGanaA(unPokemon.especie.tipoPrincipal) || piedra.tipo.leGanaA(unPokemon.especie.tipoSecundario)) {
          unPokemon.estado(Envenenado)
        }
      }
      case _ =>
    }
    
    unPokemon
  }
}