package tadp_pokemon

class Ataque(
    val tipo: TipoPokemon, 
    var puntosDeAtaqueMaximo: Integer,
    var puntosDeAtaque:Integer,
    val efectoColateral: EfectoColateralAtaque
    ) {
    
  def esDeTipo(unTipo: TipoPokemon)= {
    tipo == unTipo;
  }
    
}
