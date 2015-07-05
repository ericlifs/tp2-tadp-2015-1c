package tadp_pokemon

object Descansar extends Actividad {
  
  def afectar(pokemon: Pokemon): Pokemon =    
    dormir(pokemon.foreachAtaque(_.recuperarPuntajeMaximo()))
    
  def dormir(pokemon: Pokemon): Pokemon =
    if(estaCansado(pokemon) && pokemon.estado == Neutro) quedarseDormido(pokemon) else pokemon
    
  def estaCansado(pokemon: Pokemon): Boolean = 
    pokemon.energia < 0.5*pokemon.energiaMaxima
    
  def quedarseDormido(pokemon: Pokemon): Pokemon =
    pokemon.estado(Dormido())
    
}