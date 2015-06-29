package tadp_pokemon

case class Pokemon(val nivel: Int = 0,  val experiencia: Int= 0, val genero: Genero= null ,val energia: Int= 0,val energiaMaxima: Int= 0, val peso: Int = 0, val fuerza: Int= 0 ,val velocidad: Int= 0,val estado: Estado = null,var especie: Especie=null, val ataques: List[(Ataque, Int, Int)] = List()) {
  
  
  //Los ataques van a estar representados por: (Ataque, PuntosDeAtaque, PuntosDeAtaqueMaximosDelPokemon)
  
  
  
   def nivel(unNivel: Int): Pokemon = this.copy(nivel=unNivel);
  
  
  def experiencia(unaExperiencia: Int): Pokemon=  this.copy(experiencia = unaExperiencia);
 
  
  def genero(unGenero: Genero): Pokemon = this.copy(genero = unGenero);
  
  
  def energia(unaEnergia: Int): Pokemon =  this.copy(energia = unaEnergia);
  
  
  def energiaMaxima(unaEnergiaMaxima: Int): Pokemon = this.copy(energiaMaxima = unaEnergiaMaxima);
  
  def aumentarEnergia(unaCantidad: Int): Pokemon = {
    if (this.energia + unaCantidad >= this.energiaMaxima){
      return this.energiaMaxima(energiaMaxima);
      
    } else {
      
      return this.energiaMaxima(energia + unaCantidad);
    }
  }
  
  def peso(unPeso: Int) : Pokemon =  this.copy(peso = unPeso);
  
  def fuerza(unaFuerza: Int): Pokemon =  this.copy(fuerza = unaFuerza);
  
  def velocidad(unaVelocidad: Int) : Pokemon =  this.copy(velocidad = unaVelocidad);
  
  def estado(unEstado: Estado): Pokemon = this.copy(estado = unEstado);
  
  def especie(unaEspecie: Especie): Pokemon = this.copy(especie = unaEspecie);
  
  def ataques(unosAtaques: List[Ataque]): Pokemon = {
    this.copy(ataques = unosAtaques.filter{ ataque => ataque.tipo == this.especie.tipoPrincipal}.map { ataque => (ataque, ataque.puntosDeAtaqueMaximo, ataque.puntosDeAtaqueMaximo) })
  }
  
  def aprenderAtaque(unAtaque: Ataque): Pokemon = {
    if (unAtaque.tipo == especie.tipoPrincipal || (especie.tipoSecundario != null && unAtaque.tipo == especie.tipoSecundario)){
      this.copy(ataques = ataques.::(unAtaque, unAtaque.puntosDeAtaqueMaximo, unAtaque.puntosDeAtaqueMaximo))}
    else{
      this
    }
  }
  
  
  def aumentarExperiencia(unaCantidadExperiencia: Int) {
    if (puedeSubirNivelSegunExperiencia(unaCantidadExperiencia)) {
      this.subirNivel().evolucionar().experiencia(experiencia + unaCantidadExperiencia)}
    else { 
      this.experiencia(experiencia + unaCantidadExperiencia);
    }
  }
  
  def puedeSubirNivelSegunExperiencia(unaCantidadExperiencia: Int): Boolean = {
    unaCantidadExperiencia >= experienciaNecesariaProximoNivel(nivel)
  }
  
  def experienciaNecesariaProximoNivel(unNivel: Int): Int = { 
     if (nivel >= 1) (2 * experienciaNecesariaProximoNivel(nivel - 1)) + especie.resistenciaEvolutiva
     else 0
  }
  
  def subirNivel(): Pokemon = {
    this.energiaMaxima(energiaMaxima + especie.incrementoEnergiaMaxima).peso(peso + especie.incrementoPeso).fuerza(fuerza + especie.incrementoFuerza).velocidad(velocidad + especie.incrementoVelocidad)
  }
  
  def aumentarEnergiaAlMaximo() {
    this.energia(energiaMaxima)
  }
  
  def evolucionar(): Pokemon = {
    if (especie.criterioEvolucion.criterio(this)) {
      this.especie(especie.especieCualEvoluciona)
    }else
      this
  }
}