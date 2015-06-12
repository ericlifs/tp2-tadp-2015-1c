package tadp_pokemon

object Reposar extends EfectoColateralAtaque {
  val efecto: (Pokemon => Unit) = (unPokemon: Pokemon) => {
      unPokemon.aumentarEnergiaAlMaximo()
      unPokemon.estado(Dormido)
  }
}