package tadp_pokemon

case class UsarPiedra(val piedra:PiedraEvolutiva) extends Actividad {
  
  def afectar(pokemon: Pokemon):Pokemon =
    if (debeSerEnvenenado(pokemon)) pokemon.estado(Envenenado) else pokemon 
  
  def debeSerEnvenenado(pokemon: Pokemon): Boolean =
    !pokemon.debeEvolucionarTras(this) && pokemon.especie.pierdeContra(piedra.tipo)
    
}