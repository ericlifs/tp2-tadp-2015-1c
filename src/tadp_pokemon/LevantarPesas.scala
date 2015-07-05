package tadp_pokemon

class LevantarPesas(val kilos: Integer) extends Actividad {
  
import scala.util.Try
  
  override def siPuede(pokemon: Try[Pokemon]): Try[Pokemon] =
    pokemon.filter(!_.esPrincipalmenteDe(Fantasma))
  
  def afectar(pokemon: Pokemon): Pokemon  =  
    pokemon match {
      case pokemon @ Pokemon(_,_,_,Paralizado) => pokemon.estado(KnockOut)
      case pokemon => if(kilos/pokemon.fuerza<=10) levantarPesasLivianas(pokemon) else pokemon.estado(Paralizado)
    }    
  
  def levantarPesasLivianas(pokemon: Pokemon): Pokemon = 
    pokemon match {
      case pokemon @ (Pokemon(Pelea,_,_,_) | Pokemon(_,Some(Pelea),_,_))=> pokemon.aumentarExperiencia(2*kilos)
      case pokemon => pokemon.aumentarExperiencia(kilos) 
    }
  
}
