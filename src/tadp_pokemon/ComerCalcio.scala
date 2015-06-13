package tadp_pokemon

object ComerCalcio extends Actividad {
  val realizarActividad: (Pokemon => Pokemon) = (unPokemon: Pokemon) => {
      unPokemon.velocidad(unPokemon.velocidad + 5)
        
      unPokemon
  }
}