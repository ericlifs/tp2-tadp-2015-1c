package tadp_pokemon

object UsarPocion extends Actividad {
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
      unPokemon.energia(unPokemon.energia + 50)
      unPokemon
  }
}