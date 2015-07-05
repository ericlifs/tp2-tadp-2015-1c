package tadp_pokemon

trait PiedraEvolutiva

case class PiedraComun(val tipo:TipoPokemon) extends PiedraEvolutiva

case object PiedraLunar extends PiedraEvolutiva
