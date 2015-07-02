package tadp_pokemon

case class Ataque(
    val tipo: TipoPokemon, 
    val puntosDeAtaqueMaximo: Integer,
    val puntosDeAtaque:Integer,
    val efectoColateral: EfectoColateralAtaque
    ) {
    
  def esDeTipo(unTipo: TipoPokemon)= 
    tipo == unTipo;
  
  def aumentarMaximo(puntos: Integer): Ataque = 
    copy(puntosDeAtaqueMaximo = puntosDeAtaqueMaximo + puntos)
    
}
