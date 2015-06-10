package tadp_pokemon

class Ataque(unTipoPokemon: TipoPokemon, unosPuntosDeAtaqueMaximo: Integer, unEfectoColateral: Pokemon => Unit) {
  
  val tipo: TipoPokemon = unTipoPokemon
  
  val puntosDeAtaqueMaximo: Int = unosPuntosDeAtaqueMaximo
  
  val efectoColateral: Pokemon => Unit = unEfectoColateral
}