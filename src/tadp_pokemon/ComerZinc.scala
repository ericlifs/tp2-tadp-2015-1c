package tadp_pokemon

object ComerZinc extends Actividad {
  
  def realizarActividad(pokemon: Pokemon): Pokemon =
    pokemon.foreachAtaque(_.aumentarMaximo(2))
        
}