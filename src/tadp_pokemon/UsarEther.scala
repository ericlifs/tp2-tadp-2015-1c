package tadp_pokemon

object UsarEther extends Actividad {
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
      if (unPokemon.estado != KnockOut)
        unPokemon.estado(null)
        
      unPokemon
  }
}