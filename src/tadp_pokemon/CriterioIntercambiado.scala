package tadp_pokemon

case object CriterioIntercambiado extends CriterioEvolucion {
  
  val criterio: (Pokemon => Boolean) = (unPokemon: Pokemon) => ???
}