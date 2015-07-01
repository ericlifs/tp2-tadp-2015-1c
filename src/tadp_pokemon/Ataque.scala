package tadp_pokemon

class Ataque(unTipoPokemon: TipoPokemon, unosPuntosDeAtaqueMaximo: Integer, unEfectoColateral: EfectoColateralAtaque = null) {
  
  val tipo: TipoPokemon = unTipoPokemon
  
  val puntosDeAtaqueMaximo: Int = unosPuntosDeAtaqueMaximo
  
  val efectoColateral: EfectoColateralAtaque = unEfectoColateral
  
  def esDeTipo(unTipo: TipoPokemon)= {
    tipo == unTipo;
  }
    
}