package tadp_pokemon

case object CriterioExpuestoPiedraComun extends CriterioEvolucion {
  
  def debeEvolucionarTras(pokemon: Pokemon,actividad: Actividad): Boolean =
    actividad match{
    case UsarPiedra(piedra:PiedraComun) => pokemon.esPrincipalmenteDe(piedra.tipo)
    case _ => false
    }
  
}

case object CriterioExpuestoPiedraLunar extends CriterioEvolucion {
  
  def debeEvolucionarTras(pokemon: Pokemon,actividad: Actividad): Boolean =
    actividad match{
    case UsarPiedra(PiedraLunar) => true
    case _ => false
    }
  
}