package tadp_pokemon

object Endurecerse extends EfectoColateralAtaque {
  val efecto: (Pokemon => Unit) = (unPokemon: Pokemon) => {
      unPokemon.energia(unPokemon.energia + 5)
      unPokemon.estado(Paralizado)
  }
}