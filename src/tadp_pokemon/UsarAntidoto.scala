package tadp_pokemon

object UsarAntidoto extends Actividad {
  
  def afectar(pokemon: Pokemon):Pokemon =
      pokemon.estado match{
        case Envenenado => pokemon.estado(Neutro)
        case _ => pokemon      
      }
}