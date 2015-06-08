package tadp_pokemon

class EspecieFactory {
  
  var incrementoEnergiaMaxima: Int = _
  
  var incrementoPeso: Int = _
  
  var incrementoFuerza: Int = _
  
  var incrementoVelocidad: Int = _
  
  var resistenciaEvolutiva: Int = _
  
  var pesoMaximo: Int = _
  
  var tipoPrincipal: TipoPokemon = _
  
  var tipoSecundario: TipoPokemon = null
  
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
    
    return especie
  }
 
}