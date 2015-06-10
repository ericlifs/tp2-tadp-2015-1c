package tadp_pokemon

class CriterioExpuestoPiedra extends CriterioEvolucion {
  
  val criterio: (Pokemon => Boolean) = (unPokemon: Pokemon) => 
      unPokemon.piedrasExpuesto.exists { 
        piedra => piedra match {
          case PiedraLunar => true
          case _ => piedra.tipo == unPokemon.especie.tipoPrincipal
        }
      }
}