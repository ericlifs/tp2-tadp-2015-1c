package tadp_pokemon

object ComerHierro extends Actividad {
  
  def afectar(pokemon: Pokemon): Pokemon = 
    pokemon.aumentarFuerza(5)
 
}