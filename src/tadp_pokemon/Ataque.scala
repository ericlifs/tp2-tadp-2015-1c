package tadp_pokemon

class Ataque(val ataqueBase: AtaqueBase) {
  
  var puntosDeAtaqueMaximo: Integer = ataqueBase.puntosDeAtaqueMaximoInicial;
  var puntosDeAtaque:Integer = ataqueBase.puntosDeAtaqueMaximoInicial;
  
  def aumentarMaximo(puntos: Integer) = 
    puntosDeAtaqueMaximo +=  puntos
    
  def perderPuntos(puntos: Integer) =
    puntosDeAtaque -= puntos
    
  def recuperarPuntajeMaximo() =
    puntosDeAtaque = puntosDeAtaqueMaximo
    
  def esBasicamente(unAtaqueBase: AtaqueBase) = 
    ataqueBase == unAtaqueBase;
  
  def estaEnCero() =
    puntosDeAtaque <= 0
    
}

case class AtaqueBase(
    val tipo: TipoPokemon, 
    val puntosDeAtaqueMaximoInicial: Integer,
    val efectoColateral: Pokemon => Pokemon = identity
    ) {
    
  def esDeTipo(unTipo: TipoPokemon)= 
    tipo == unTipo;
  
}
