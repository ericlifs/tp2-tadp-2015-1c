package tadp_pokemon

import scala.util.{Try,Success,Failure}

trait Rutina{
  def realizar(pokemon: Pokemon): Try[Pokemon]
}

case class RutinaComun(val actividades: List[Actividad] = List(), val nombre: String = "No me pusieron nombre :(") extends Rutina{
    
  def realizar(pokemon: Pokemon): Try[Pokemon] =
    actividades.foldLeft[Try[Pokemon]](Success(pokemon)){
    (pokemon:Try[Pokemon], actividad: Actividad) =>
      actividad.realizarActividad(pokemon)    
    }
  
}

case object RutinaVacia extends Rutina{
  
    def realizar(pokemon: Pokemon): Try[Pokemon] = Success(pokemon)
  
}