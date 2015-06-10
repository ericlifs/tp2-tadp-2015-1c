package tadp_pokemon

class Enfocarse extends EfectoColateralAtaque {
  val efecto: (Pokemon => Unit) = (unPokemon: Pokemon) => {
      unPokemon.velocidad(unPokemon.velocidad + 1)
  }
}