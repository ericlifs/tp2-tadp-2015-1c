package tadp_pokemon

import scala.util.Try

trait Actividad {
  
  def realizarActividadSiPuede(pokemon: Try[Pokemon]): Try[Pokemon] = 
    pokemon.filter(puedeSerHecha(_)).map(realizarActividad(_)) // Si usamos polimorfismo param. se convierte (roughly) en pokemon.filter(_.puedeHacer(this)).map(_.realizarActividad(this))
  
  def puedeSerHecha(pokemon: Pokemon): Boolean = true
  
  def realizarActividad(pokemon: Pokemon): Pokemon
    
}

//Si hacemos un filter nuestro sería algo más onda pokemon.filter(puedeSerHecha(_), excepcion)... siendo excepcion un val de la Actividad 