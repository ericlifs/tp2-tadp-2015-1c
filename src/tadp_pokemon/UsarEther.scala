package tadp_pokemon

object UsarEther extends Actividad {

    def afectar(pokemon: Pokemon): Pokemon  =
      pokemon match{
              case Pokemon(_,_,_,KnockOut )=> pokemon
              case _ => pokemon.estado(Neutro)
            }
    
}