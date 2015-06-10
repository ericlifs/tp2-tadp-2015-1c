package tadp_pokemon

class Especie {
  var incrementoEnergiaMaxima: Int = 0
  
  var incrementoPeso: Int = 0
  
  var incrementoFuerza: Int = 0
  
  var incrementoVelocidad: Int = 0
  
  var resistenciaEvolutiva: Int = 0
  
  var pesoMaximo: Int = 0
  
  var tipoPrincipal: TipoPokemon = _
  
  var tipoSecundario: TipoPokemon = null
  
  var especieCualEvoluciona: Especie = null
  
  var criterioEvolucion: CriterioEvolucion = _
  
  def incrementoEnergiaMaxima(unIncrementoEnergiaMaxima: Int) {
    incrementoEnergiaMaxima = unIncrementoEnergiaMaxima
  }
  
  def incrementoPeso(unIncrementoPeso: Int) {
    incrementoPeso = unIncrementoPeso
  }
  
  def pesoMaximo(unPesoMaximo: Int) {
    pesoMaximo = unPesoMaximo
  }
  
  def incrementoFuerza(unIncrementoFuerza: Int) {
    incrementoFuerza = unIncrementoFuerza
  }
  
  def incrementoVelocidad(unIncrementoVelocidad: Int) {
    incrementoVelocidad = unIncrementoVelocidad
  }
  
  def resistenciaEvolutiva(unaResistenciaEvolutiva: Int) {
    resistenciaEvolutiva = unaResistenciaEvolutiva
  }
  
  def tipoPrincipal(unTipoPrincipal: TipoPokemon) {
    tipoPrincipal = unTipoPrincipal
  }
  
  def tipoSecundario(unTipoSecundario: TipoPokemon) {
    tipoSecundario = unTipoSecundario
  }
  
  def especieCualEvoluciona(unaEspeciCualEvoluciona: Especie) {
    especieCualEvoluciona = unaEspeciCualEvoluciona
  }
  
  def criterioEvolucion(unCriterioEvolucion: CriterioEvolucion) {
    criterioEvolucion = unCriterioEvolucion
  }
}