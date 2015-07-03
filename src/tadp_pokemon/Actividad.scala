package tadp_pokemon

import scala.util.Try

trait Actividad {
  
  def siPuede(pokemon: Try[Pokemon]): Try[Pokemon] = pokemon
  
  def realizarActividad(pokemon: Try[Pokemon]): Try[Pokemon] =
    pokemon.flatMap(_.hacerActividad(this)).map(_.evolucionarSiDebeTras(this))
    
  def afectarSiPuede(pokemon: Try[Pokemon]): Try[Pokemon] =
    siPuede(pokemon).map(afectar(_)).filter(_.esValido())
  
  def afectar(pokemon: Pokemon): Pokemon
  
}