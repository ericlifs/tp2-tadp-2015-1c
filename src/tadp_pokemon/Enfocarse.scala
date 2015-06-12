package tadp_pokemon

object Enfocarse extends EfectoColateralAtaque {
  val efecto: (Pokemon => Unit) = (unPokemon: Pokemon) => {
      unPokemon.velocidad(unPokemon.velocidad + 1)
  }
}