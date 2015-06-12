package tadp_pokemon

class Pokemon() {
  
  var nivel: Int = _
  
  var experiencia: Int = _
  
  var genero: Genero = _
  
  var energia: Int = _
  
  var energiaMaxima: Int = _
  
  var peso: Int = _
  
  var fuerza: Int = _
  
  var velocidad: Int = _
  
  var estado: Estado = _
  
  //Los ataques van a estar representados por: (Ataque, PuntosDeAtaque, PuntosDeAtaqueMaximosDelPokemon)
  var ataques: List[(Ataque, Int, Int)] = List()
  
  var piedrasExpuesto: List[PiedraEvolutiva] = List()
  
  var especie: Especie = _
  
  def nivel(unNivel: Int) {
    nivel = unNivel
  }
  
  def experiencia(unaExperiencia: Int) {
    experiencia = unaExperiencia
  }
  
  def genero(unGenero: Genero) {
    genero = unGenero
  }
  
  def energia(unaEnergia: Int) {
    energia = unaEnergia
  }
  
  def energiaMaxima(unaEnergiaMaxima: Int) {
    energiaMaxima = unaEnergiaMaxima
  }
  
  def aumentarEnergia(unaCantidad: Int){
    if (energia + unaCantidad >= energiaMaxima){
      energia = energiaMaxima
    } else {
      energia += unaCantidad
    }
  }
  
  def peso(unPeso: Int) {
    peso = unPeso
  }
  
  def fuerza(unaFuerza: Int) {
    fuerza = unaFuerza
  }
  
  def velocidad(unaVelocidad: Int) {
    velocidad = unaVelocidad
  }
  
  def estado(unEstado: Estado) {
    estado = unEstado
  }
  
  def especie(unaEspecie: Especie) {
    especie = unaEspecie
  }
  
  def ataques(unosAtaques: List[Ataque]) {
    ataques = unosAtaques.filter{ ataque => ataque.tipo == this.especie.tipoPrincipal}.map { ataque => (ataque, ataque.puntosDeAtaqueMaximo, ataque.puntosDeAtaqueMaximo) }
  }
  
  def aprenderAtaque(unAtaque: Ataque) {
    if (unAtaque.tipo == especie.tipoPrincipal || (especie.tipoSecundario != null && unAtaque.tipo == especie.tipoSecundario))
      ataques = ataques.::(unAtaque, unAtaque.puntosDeAtaqueMaximo, unAtaque.puntosDeAtaqueMaximo)
  }
  
  def fueExpuestoAPiedra(unaPiedraEvolutiva: PiedraEvolutiva) {
    piedrasExpuesto = piedrasExpuesto.::(unaPiedraEvolutiva)
  }
  
  def aumentarExperiencia(unaCantidadExperiencia: Int) {
    if (puedeSubirNivelSegunExperiencia(unaCantidadExperiencia)) {
      subirNivel()
      evolucionar()
    }
    
    experiencia(experiencia + unaCantidadExperiencia)
  }
  
  def puedeSubirNivelSegunExperiencia(unaCantidadExperiencia: Int): Boolean = {
    unaCantidadExperiencia >= experienciaNecesariaProximoNivel(nivel)
  }
  
  def experienciaNecesariaProximoNivel(unNivel: Int): Int = { 
     if (nivel >= 1) (2 * experienciaNecesariaProximoNivel(nivel - 1)) + especie.resistenciaEvolutiva
     else 0
  }
  
  def subirNivel() {
    energiaMaxima(energiaMaxima + especie.incrementoEnergiaMaxima)
    peso(peso + especie.incrementoPeso)
    fuerza(fuerza + especie.incrementoFuerza)
    velocidad(velocidad + especie.incrementoVelocidad)
  }
  
  def atacarA(unPokemon: Pokemon, unAtaque: Ataque) {
    var ataquePokemon = ataques.find((ataque: (Ataque, Int, Int)) => ataque._1 == unAtaque)
    
    if (ataquePokemon != None) {
      var puntosAtaquePokemon = ataquePokemon.get._2
      
      if (puntosAtaquePokemon > 0) {
        
        if (unAtaque.efectoColateral != null)
          unAtaque.efectoColateral.efecto(this)
       
        this.ataques = ataques.map{ ataque => 
          if (ataque._1 == unAtaque)
            (ataque._1, ataque._2 - 1, ataque._3)
          else
            ataque
        }
      }
    }
  }
  
  def aumentarEnergiaAlMaximo() {
    energia(energiaMaxima)
  }
  
  def evolucionar() {
    if (especie.criterioEvolucion.criterio(this)) {
      especie(especie.especieCualEvoluciona)
    }
  }
}