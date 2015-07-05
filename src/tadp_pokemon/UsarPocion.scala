package tadp_pokemon

case class UsarPocion(val energiaRecuperada: Int) extends Actividad {
    
  def afectar(pokemon: Pokemon): Pokemon =
    pokemon.aumentarEnergia(energiaRecuperada.min(50))
    
}