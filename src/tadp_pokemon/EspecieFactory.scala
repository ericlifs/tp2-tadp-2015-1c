package tadp_pokemon

class EspecieFactory {
  
  var incrementoEnergiaMaxima: Int = _
  
  var incrementoPeso: Int = _
  
  var incrementoFuerza: Int = _
  
  var incrementoVelocidad: Int = _
  
  var resistenciaEvolutiva: Int = _
  
  var pesoMaximo: Int = _
  
  var tipoPrincipal: TipoPokemon = _
  
  var tipoSecundario: TipoPokemon = _
  
  var especieCualEvoluciona: Especie = _
  
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
  
  def construirEspecie() : Especie = {
      
    def especie = new Especie()
    
    especie.incrementoEnergiaMaxima(this.incrementoEnergiaMaxima)
    especie.incrementoPeso(this.incrementoPeso)
    especie.incrementoFuerza(this.incrementoFuerza)
    especie.incrementoVelocidad(this.incrementoVelocidad)
    especie.pesoMaximo(this.pesoMaximo)
    especie.tipoPrincipal(this.tipoPrincipal)
    especie.tipoSecundario(this.tipoSecundario)
    especie.resistenciaEvolutiva(this.resistenciaEvolutiva)
    especie.especieCualEvoluciona(this.especieCualEvoluciona)
    especie.criterioEvolucion(this.criterioEvolucion)
    
    return especie
  }
 
}