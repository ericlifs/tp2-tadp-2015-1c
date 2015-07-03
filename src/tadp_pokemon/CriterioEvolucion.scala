package tadp_pokemon

trait CriterioEvolucion {
  
  def debeEvolucionarTras(pokemon: Pokemon,actividad: Actividad): Boolean
  
}