package tadp_pokemon

case class UsarPiedra(val piedra:PiedraEvolutiva) extends Actividad {
  
  def afectar(pokemon: Pokemon):Pokemon =
    if (!pokemon.debeEvolucionarTras(this)) pokemon.estado(Envenenado) else pokemon 
  
}