package tadp_pokemon

case object CriterioIntercambiado extends CriterioEvolucion {
  
  def debeEvolucionarTras(pokemon: Pokemon,actividad: Actividad): Boolean =
    actividad match{
    case FingirIntercambio => true
    case _ => false    
    }
      
}