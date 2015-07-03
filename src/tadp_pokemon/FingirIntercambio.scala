package tadp_pokemon

object FingirIntercambio extends Actividad {
  
  def afectar(pokemon: Pokemon): Pokemon =
    if (pokemon.debeEvolucionarTras(this)) pokemon else decaer(pokemon).estado(Triste) 
  
  def decaer(pokemon: Pokemon):Pokemon = 
    pokemon.genero match {
            case Macho => pokemon.aumentarPeso(1)
            case Hembra => pokemon.aumentarPeso(-10)
          }
}