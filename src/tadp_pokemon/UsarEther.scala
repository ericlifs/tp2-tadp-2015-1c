package tadp_pokemon

object UsarEther extends Actividad {

    def realizarActividad(pokemon: Pokemon): Pokemon =
      pokemon.estado match{
        case KnockOut => pokemon
        case _ => pokemon.estado(Neutro)      
      }
    
}