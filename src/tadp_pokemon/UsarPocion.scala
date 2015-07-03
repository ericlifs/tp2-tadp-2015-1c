package tadp_pokemon

object UsarPocion extends Actividad {
    
  def afectar(pokemon: Pokemon): Pokemon =
    pokemon.aumentarEnergia(50)
    
}