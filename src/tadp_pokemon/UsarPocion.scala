package tadp_pokemon

object UsarPocion extends Actividad {
    
  def realizarActividad(pokemon: Pokemon): Pokemon =
    pokemon.aumentarEnergia(50)
    
}