package tadp_pokemon

object AprenderAtaque extends Actividad {
  
  var ataque: Ataque = null
  
  def ataque (unAtaque: Ataque) {
    ataque = unAtaque
  }
  
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
      if (ataque.tipo.esAfin(unPokemon.especie))
        unPokemon.aprenderAtaque(ataque)
      else
        unPokemon.estado(KnockOut)
        
      unPokemon
  }
}
