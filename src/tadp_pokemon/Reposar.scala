package tadp_pokemon

class Reposar extends EfectoColateralAtaque {
  val efecto: (Pokemon => Unit) = (unPokemon: Pokemon) => {
      unPokemon.aumentarEnergiaAlMaximo()
      unPokemon.estado(Dormido)
  }
}