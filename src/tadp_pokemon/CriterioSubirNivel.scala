package tadp_pokemon

//Se decide dejar acá el nivel de evolución de la especie porque sino todas las especies tendrían un nivelEvolución cuando es posible
//que nisiquiera tengan como criterio 'CriterioSubirNivel', es decir, se evita que posean información que no utilizan
class CriterioSubirNivel(unNivel: Integer) extends CriterioEvolucion {
  var nivel: Integer = unNivel
  
  val criterio: (Pokemon => Boolean) = (unPokemon: Pokemon) => unPokemon.nivel >= this.nivel
}