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
    if(energia+unaCantidad>=energiaMaxima){
      energia = energiaMaxima
    }else{
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
}