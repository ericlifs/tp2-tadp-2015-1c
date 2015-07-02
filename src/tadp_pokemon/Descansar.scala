package tadp_pokemon

object Descansar extends Actividad {
  
  def realizarActividad(pokemon: Pokemon): Pokemon =    
    pokemon.foreachAtaque{ ataque => ataque.copy(puntosDeAtaque = ataque.puntosDeAtaqueMaximo)}.dormirse()
    
}