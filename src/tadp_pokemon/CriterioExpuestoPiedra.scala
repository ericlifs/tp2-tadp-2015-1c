package tadp_pokemon

case object CriterioExpuestoPiedra extends CriterioEvolucion {
  
  val criterio: (Pokemon => Boolean) = (unPokemon: Pokemon) => ???
}