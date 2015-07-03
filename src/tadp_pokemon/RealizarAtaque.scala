package tadp_pokemon

import scala.util.Try

class RealizarAtaque(val ataqueBase: AtaqueBase) extends Actividad {
    
  def siPuede(pokemon: Try[Pokemon]): Try[Pokemon] =
    pokemon.filter(noPuedeAtacar(_, ataqueBase))
  
  def afectar(pokemon: Pokemon): Pokemon  = {
    pokemon.ataques.find(_.esBasicamente(ataqueBase)).get.perderPuntos(1)
    ataqueBase.efectoColateral(pokemon.aumentarExperiencia(experienciaPara(pokemon)))  
  }
  
  def experienciaPara(pokemon:Pokemon): Integer = 
    if (pokemon.especie.esSecundariamenteDe(ataqueBase.tipo)) 
      experienciaPorGenero(pokemon) 
    else ataqueBase match{
      case AtaqueBase(Dragon,_,_) => 80
      case AtaqueBase(pokemon.especie.tipoPrincipal,_,_) => 50
      case _ => 0
    }
  
  def experienciaPorGenero(pokemon:Pokemon): Integer = 
    pokemon.genero match{
      case Hembra => 40
      case Macho => 20
    }  
    
  def noPuedeAtacar(pokemon: Pokemon,tipoAtaque: AtaqueBase): Boolean = 
    !pokemon.ataques.exists{ ataque => ataque.esBasicamente(ataqueBase) && !ataque.estaEnCero}
    
}