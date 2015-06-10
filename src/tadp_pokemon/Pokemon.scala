package tadp_pokemon

class Pokemon() {
  
  var nivel: Int = 0
  
  var experiencia: Int = 0
  
  var genero: String = ""
  
  var energia: Int = 0
  
  var energiaMaxima: Int = 0
  
  var peso: Int = 0
  
  var fuerza: Int = 0
  
  var velocidad: Int = 0
  
  var estado: Estado = _
  
  //Los ataques van a estar representados por: (Ataque, PuntosDeAtaque, PuntosDeAtaqueMaximosDelPokemon)
  var ataques: List[(Ataque, Int, Int)] = _
  
  var piedrasExpuesto: List[PiedraEvolutiva] = _
  
  var especie: Especie = _
  
  def nivel(unNivel: Int) {
    nivel = unNivel
  }
  
  def experiencia(unaExperiencia: Int) {
    experiencia = unaExperiencia
  }
  
  def genero(unGenero: String) {
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
    if (unaCantidadExperiencia >= especie.resistenciaEvolutiva)
      subirNivel()
    
    experiencia(experiencia + unaCantidadExperiencia)
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
}