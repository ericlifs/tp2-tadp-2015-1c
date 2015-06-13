package tadp_pokemon

object UsarAntidoto extends Actividad {
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
      if (unPokemon.estado == Envenenado)
        unPokemon.estado(null)
        
      unPokemon
  }
}