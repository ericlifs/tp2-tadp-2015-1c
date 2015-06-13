package tadp_pokemon

//Se decide dejar acá el nivel de evolución de la especie porque sino todas las especies tendrían un nivelEvolución cuando es posible
//que nisiquiera tengan como criterio 'CriterioSubirNivel', es decir, se evita que posean información que no utilizan
case object CriterioSubirNivel extends CriterioEvolucion {
  var nivel: Integer = 0
  
  def nivel(unNivel: Integer) {
    nivel = unNivel
  }
  
  val criterio: (Pokemon => Boolean) = (unPokemon: Pokemon) => unPokemon.nivel >= this.nivel
}