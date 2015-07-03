package tadp_pokemon

object FingirIntercambio extends Actividad {
  
  def afectar(pokemon: Pokemon): Pokemon =
    if (pokemon.debeEvolucionarTras(this)) pokemon else decaer(pokemon).estado(Triste) 
  
  def decaer(pokemon: Pokemon):Pokemon = 
    pokemon match {
            case pokemon @ Pokemon(_,_,Macho,_) => pokemon.aumentarPeso(1)
            case pokemon @ Pokemon(_,_,Hembra,_) => pokemon.aumentarPeso(-10)
          }
}