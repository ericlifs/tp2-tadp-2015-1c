package tadp_pokemon

case object CriterioExpuestoPiedra extends CriterioEvolucion {
  
  def debeEvolucionarTras(pokemon: Pokemon,actividad: Actividad): Boolean =
    actividad match{
    case UsarPiedra(PiedraLunar) => true
    case UsarPiedra(piedra) => pokemon.esPrincipalmenteDe(piedra.tipo)
    case _ => false
    }
  
}