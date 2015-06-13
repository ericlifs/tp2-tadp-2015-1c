package tadp_pokemon

object Nadar extends Actividad {
  
  var minutosNado: Int = 0
  
  def minutosNado (unosMinutosNado: Int) {
    minutosNado = unosMinutosNado
  }
  
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {

    if (Agua.leGanaA(unPokemon.especie.tipoPrincipal) || Agua.leGanaA(unPokemon.especie.tipoSecundario))
      unPokemon.estado(KnockOut)
    else {
      for (i <- 1 to minutosNado){
        unPokemon.energia(unPokemon.energia - 1)
        unPokemon.experiencia(unPokemon.experiencia + 200)
      }
      
      if (unPokemon.especie.tipoPrincipal == Agua)
        unPokemon.velocidad(unPokemon.velocidad + (minutosNado / 60))
    }
    
    unPokemon
  }
}