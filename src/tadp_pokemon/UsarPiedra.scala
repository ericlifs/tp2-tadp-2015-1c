package tadp_pokemon

case class UsarPiedra(val piedra:PiedraEvolutiva) extends Actividad {
  
  def afectar(pokemon: Pokemon):Pokemon =
    if (!pokemon.debeEvolucionarTras(this) && debeSerEnvenenado(pokemon)) pokemon.estado(Envenenado) else pokemon 
  
  def debeSerEnvenenado(pokemon: Pokemon): Boolean =
    piedra match{
      case PiedraComun(tipo) => pokemon.especie.pierdeContra(tipo)
      case _ => false
    }

}