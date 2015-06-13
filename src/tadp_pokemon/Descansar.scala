package tadp_pokemon

object Descansar extends Actividad {
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
      unPokemon.ataques.map { ataque => 
        (ataque._1, ataque._3, ataque._3)
      }
      
      if(unPokemon.estado == null && ((unPokemon.energiaMaxima * 0.5) >= unPokemon.energia))
        unPokemon.estado(Dormido)
        
      unPokemon
  }
}