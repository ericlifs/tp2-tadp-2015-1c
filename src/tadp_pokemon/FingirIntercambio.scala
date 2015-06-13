package tadp_pokemon

object FingirIntercambio extends Actividad {
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
      unPokemon.especie.criterioEvolucion match {
        case CriterioIntercambiado => unPokemon.evolucionar()
        case _ => {
          unPokemon.estado(Triste)
          unPokemon.genero match {
            case Macho => unPokemon.peso(unPokemon.peso + 1)
            case Hembra => unPokemon.peso(unPokemon.peso - 10)
          }
        }
      }
        
      unPokemon
  }
}