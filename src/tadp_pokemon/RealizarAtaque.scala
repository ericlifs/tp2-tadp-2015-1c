package tadp_pokemon

import scala.util.Try

case class RealizarAtaque(val ataqueBase: AtaqueBase) extends Actividad {
    
  override def siPuede(pokemon: Try[Pokemon]): Try[Pokemon] =
    pokemon.filter(puedeAtacar(_, ataqueBase))
  
  def afectar(pokemon: Pokemon): Pokemon  = {
    pokemon.ataques.find(_.esBasicamente(ataqueBase)).map(_.perderPuntos(1))
    ataqueBase.efectoColateral(pokemon.aumentarExperiencia(experienciaPara(pokemon)))  
  }
  
  def experienciaPara(pokemon:Pokemon): Integer = 
    ataqueBase match{
      case AtaqueBase(Dragon,_,_) => 80
      case _ => experienciaSegunPokemon(pokemon)
    }
  
  def experienciaSegunPokemon(pokemon:Pokemon): Integer = 
		pokemon match{
  	  case Pokemon(ataqueBase.tipo,_,_,_) => 50
  	  case Pokemon(_,Some(ataqueBase.tipo),Macho,_) =>20
      case Pokemon(_,Some(ataqueBase.tipo),Hembra,_) =>40
      case _ => 0
    }    
   
  def puedeAtacar(pokemon: Pokemon,tipoAtaque: AtaqueBase): Boolean = 
    pokemon.ataques.exists{ ataque => ataque.esBasicamente(ataqueBase) && !ataque.estaEnCero}
    
}