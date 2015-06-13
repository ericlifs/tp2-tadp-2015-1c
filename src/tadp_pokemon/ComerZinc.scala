package tadp_pokemon

object ComerZinc extends Actividad {
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
      unPokemon.ataques.map { ataque => 
        (ataque._1, ataque._2, ataque._3 + 2)
      }
        
      unPokemon
  }
}