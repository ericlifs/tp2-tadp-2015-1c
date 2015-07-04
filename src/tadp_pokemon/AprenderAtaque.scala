package tadp_pokemon

case class AprenderAtaque(val ataqueBase: AtaqueBase) extends Actividad {
  
  def afectar(pokemon: Pokemon): Pokemon  =
    if (pokemon.especie.esAfin(ataqueBase)) pokemon.aprenderAtaque(ataqueBase) else pokemon.estado(KnockOut)
      
}
