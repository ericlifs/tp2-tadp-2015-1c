package tadp_pokemon

class CriterioSubirNivel(unNivel: Integer) extends CriterioEvolucion {
  var nivel: Integer = unNivel
  
  val criterio: (Pokemon => Boolean) = (unPokemon: Pokemon) => unPokemon.nivel >= this.nivel
}