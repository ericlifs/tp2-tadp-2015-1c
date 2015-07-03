package tadp_pokemon

case class CriterioSubirNivel(val nivel:Integer) extends CriterioEvolucion {

  def debeEvolucionarTras(pokemon: Pokemon,actividad: Actividad): Boolean=
    pokemon.nivel>=nivel
    
}