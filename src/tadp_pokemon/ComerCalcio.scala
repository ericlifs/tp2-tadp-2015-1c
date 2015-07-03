package tadp_pokemon

object ComerCalcio extends Actividad {

  def afectar(pokemon: Pokemon): Pokemon =
    pokemon.aumentarVelocidad(5)
    
}