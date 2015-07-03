package tadp_pokemon

import scala.util.{Try,Success,Failure}

class Rutina extends Actividad{
  var actividades: List[Actividad] = List()
  var nombre: String = ""
  
  def siPuede(pokemon: Try[Pokemon]): Try[Pokemon] = {
    actividades.foldLeft(pokemon)((unPokemon, actividad) => actividad.siPuede(unPokemon))
  }
  
  val afectar: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
    actividades.foldLeft(unPokemon)((pokemon,actividad)=> actividad.afectar(pokemon))
    unPokemon
  }
}