package tadp_pokemon

case class Pokemon(
    val nivel: Int = 0, val experiencia: Int = 0, val genero: Genero, val energia: Int = 0, 
    val energiaMaxima: Int = 0, val peso: Int = 0, val fuerza: Int = 0, val velocidad: Int = 0, 
    val estado: Estado, val especie: Especie, val ataques: List[Ataque] = List()
    ) {
 
  def nivel(unNivel: Int): Pokemon = copy(nivel = unNivel)

  def energia(unaEnergia: Int): Pokemon = copy(energia = unaEnergia)
  
  def estado(unEstado: Estado): Pokemon = copy(estado = unEstado)

  def aumentarEnergiaMaxima(unaEnergiaMaxima: Int): Pokemon = copy(energiaMaxima = energiaMaxima+unaEnergiaMaxima)
 
  def aumentarFuerza(unaFuerza: Int): Pokemon = copy(fuerza = fuerza + unaFuerza)
  
  def aumentarVelocidad(unaVelocidad: Int): Pokemon = copy(velocidad = velocidad + unaVelocidad)
  
  def aumentarPeso(unPeso: Int): Pokemon = copy(peso = (peso + unPeso).min(especie.pesoMaximo))
  
  def aumentarEnergia(unaCantidad: Int): Pokemon = energia((energia+unaCantidad).min(energiaMaxima))

  def ataques(unosAtaques: List[Ataque]): Pokemon = {
    this.copy(ataques = unosAtaques.filter{ataque => ataque.tipo == this.especie.tipoPrincipal })
  }

  def aprenderAtaque(unAtaque: Ataque): Pokemon = 
    if(especie.esAfin(unAtaque)) copy(ataques = ataques:+unAtaque) else this

  def aumentarExperiencia(unaCantidadExperiencia: Int) = 
    copy(experiencia = experiencia + unaCantidadExperiencia).subirNivel(unaCantidadExperiencia)
    
  def subirNivel(unaCantidadExperiencia: Int) =
      if (puedeSubirNivelSegunExperiencia(unaCantidadExperiencia)) aumentarCaracteristicas() else this

  def puedeSubirNivelSegunExperiencia(unaCantidadExperiencia: Int): Boolean = 
    unaCantidadExperiencia >= experienciaNecesariaProximoNivel(nivel)

  def experienciaNecesariaProximoNivel(unNivel: Int): Int = 
    if (nivel >= 1) (2 * experienciaNecesariaProximoNivel(nivel - 1)) + especie.resistenciaEvolutiva else 0

  def aumentarCaracteristicas(): Pokemon = 
    aumentarEnergiaMaxima(especie.incrementoEnergiaMaxima)
    .aumentarPeso(especie.incrementoPeso)
    .aumentarFuerza(especie.incrementoFuerza)
    .aumentarVelocidad(especie.incrementoVelocidad)

  def aumentarEnergiaAlMaximo() = energia(energiaMaxima)

  def evolucionar(): Pokemon = {
    if (especie.criterioEvolucion.criterio(this)) {
      this.especie(especie.especieCualEvoluciona)
    } else
      this
  }
}