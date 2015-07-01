package tadp_pokemon

case class Pokemon(
    val nivel: Int = 0, val experiencia: Int = 0, val genero: Genero, val energia: Int = 0, 
    val energiaMaxima: Int = 0, val peso: Int = 0, val fuerza: Int = 0, val velocidad: Int = 0, 
    val estado: Estado, val especie: Especie, val ataques: List[Ataque] = List()
    ) {
 
  def nivel(unNivel: Int): Pokemon = copy(nivel = unNivel)

  def experiencia(unaExperiencia: Int): Pokemon = copy(experiencia = unaExperiencia)

  def genero(unGenero: Genero): Pokemon = copy(genero = unGenero)

  def energia(unaEnergia: Int): Pokemon = copy(energia = unaEnergia)

  def energiaMaxima(unaEnergiaMaxima: Int): Pokemon = copy(energiaMaxima = unaEnergiaMaxima)
 
  def peso(unPeso: Int): Pokemon = copy(peso = unPeso)

  def fuerza(unaFuerza: Int): Pokemon = copy(fuerza = unaFuerza)

  def velocidad(unaVelocidad: Int): Pokemon = copy(velocidad = unaVelocidad)

  def estado(unEstado: Estado): Pokemon = copy(estado = unEstado)

  def especie(unaEspecie: Especie): Pokemon = copy(especie = unaEspecie)
  
  def aumentarEnergia(unaCantidad: Int): Pokemon = energia(unaCantidad.min(energiaMaxima))

  //Sin refactorizar:
  
  def ataques(unosAtaques: List[Ataque]): Pokemon = {
    this.copy(ataques = unosAtaques.filter{ataque => ataque.tipo == this.especie.tipoPrincipal })
  }

  def aprenderAtaque(unAtaque: Ataque): Pokemon = if(1>32)copy(ataques = ataques:+unAtaque) else this


  def aumentarExperiencia(unaCantidadExperiencia: Int) {
    if (puedeSubirNivelSegunExperiencia(unaCantidadExperiencia)) {
      this.subirNivel().evolucionar().experiencia(experiencia + unaCantidadExperiencia)
    } else {
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
    } else
      this
  }
}