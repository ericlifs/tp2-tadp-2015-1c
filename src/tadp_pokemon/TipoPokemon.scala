package tadp_pokemon

abstract class TipoPokemon (val tiposQueLesGana: List[TipoPokemon] = List()) 

case object Normal extends TipoPokemon()
case object Fuego extends TipoPokemon(List(Planta, Hielo, Bicho))
case object Agua extends TipoPokemon(List(Fuego, Tierra, Roca))
case object Planta extends TipoPokemon(List(Agua, Tierra, Roca))
case object Tierra extends TipoPokemon(List(Fuego, Electrico, Veneno, Roca))
case object Hielo extends TipoPokemon(List(Planta, Tierra, Volador, Dragon))
case object Roca extends TipoPokemon(List(Fuego, Hielo, Volador, Bicho))
case object Electrico extends TipoPokemon(List(Agua, Volador))
case object Psiquico extends TipoPokemon(List(Pelea, Veneno))
case object Pelea extends TipoPokemon(List(Normal, Hielo, Roca))
case object Volador extends TipoPokemon(List(Planta, Pelea, Bicho))
case object Bicho extends TipoPokemon(List(Planta, Psiquico))
case object Veneno extends TipoPokemon(List(Planta))
case object Dragon extends TipoPokemon(List(Dragon))
case object Fantasma extends TipoPokemon(List(Psiquico, Fantasma))