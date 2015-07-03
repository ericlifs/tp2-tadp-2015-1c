package tadp_pokemon

object ComerZinc extends Actividad {
  
  def afectar(pokemon: Pokemon): Pokemon =
    pokemon.foreachAtaque(_.aumentarMaximo(2))
        
}