package tadp_pokemon

abstract class TipoPokemon (var tiposQueLesGana: List[TipoPokemon] = List()) {
  def tiposQueLesGana(unaLista : List[TipoPokemon]){
    tiposQueLesGana = unaLista
  }
  
  def esAfin(otraEspecie: Especie): Boolean = {
    List(Normal, otraEspecie.tipoPrincipal, otraEspecie.tipoSecundario).exists { tipo => tipo == this }
  }
  
  def leGanaA(otroTipo: TipoPokemon): Boolean = {
    tiposQueLesGana.contains(otroTipo) //Esto es re Prolog: A los que me dijeron sé que les gano, y a los que no me dijeron la posta es que no sé, pero por universo cerrado te digo q no :P
  }
}

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
//salvo la recursividad
//case object Dragon extends TipoPokemon(List(Dragon)
case object Dragon extends TipoPokemon(){
  this.tiposQueLesGana = List(Dragon)
}
case object Fantasma extends TipoPokemon(){
  this.tiposQueLesGana = List(Psiquico, Fantasma)
}