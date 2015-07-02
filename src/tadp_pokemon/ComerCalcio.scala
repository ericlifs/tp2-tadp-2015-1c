package tadp_pokemon

object ComerCalcio extends Actividad {

  def realizarActividad(pokemon: Pokemon): Pokemon =
    pokemon.aumentarVelocidad(5)
}