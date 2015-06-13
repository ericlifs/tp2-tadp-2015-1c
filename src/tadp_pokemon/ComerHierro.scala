package tadp_pokemon

object ComerHierro extends Actividad {
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
      unPokemon.fuerza(unPokemon.fuerza + 5)
        
      unPokemon
  }
}