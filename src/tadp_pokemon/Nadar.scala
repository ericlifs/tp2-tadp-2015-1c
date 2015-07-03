package tadp_pokemon

class Nadar(val minutos : Integer) extends Actividad {
  
  def afectar(pokemon: Pokemon): Pokemon  = 
    if (pierdeConAgua(pokemon)) pokemon.estado(KnockOut) else completarNado(pokemon)
  
  def pierdeConAgua(pokemon:Pokemon): Boolean =
    pokemon.especie.pierdeContra(Agua)
  
  def completarNado(pokemon: Pokemon): Pokemon  = 
    pokemon match {
      case pokemon @(Pokemon(Agua,_,_,_)|Pokemon(_,Some(Agua),_,_)) => pokemon.aumentarVelocidad(minutos/60)
      case pokemon => pokemon
    }
}