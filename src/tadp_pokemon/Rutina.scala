package tadp_pokemon

import scala.util.{Try,Success,Failure}

trait Rutina{
  def realizar(pokemon: Pokemon): Try[Pokemon]
}

case class RutinaComun(val actividades: List[Actividad] = List(), val nombre: String = "No me pusieron nombre :(") extends Rutina{
  
  def realizar(pokemon: Pokemon): Try[Pokemon] ={
    actividades.map(_.actividad).foldLeft(identity[Try[Pokemon]] _)(_ compose _) (Success(pokemon))
  }
}
case object RutinaVacia extends Rutina{                                                                                                                                                                                                                                                                                                                                          
  
    def realizar(pokemon: Pokemon): Try[Pokemon] = Success(pokemon)
  
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           