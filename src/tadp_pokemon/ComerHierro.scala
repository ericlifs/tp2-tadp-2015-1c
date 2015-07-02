package tadp_pokemon

object ComerHierro extends Actividad {
  
  def realizarActividad(pokemon: Pokemon): Pokemon = 
    pokemon.aumentarFuerza(5)
 
}