package tadp_pokemon

case class Pokemon(
    val nivel: Int = 0, val experiencia: Int = 0, val genero: Genero, val energia: Int = 0, 
    val energiaMaxima: Int = 0, val peso: Int = 0, val fuerza: Int = 0, val velocidad: Int = 0, 
    val estado: Estado, val especie: Especie, val ataques: List[Ataque] = List()
    ) {
 
  def nivel(unNivel: Int): Pokemon = copy(nivel = unNivel)

  def energia(unaEnergia: Int): Pokemon = copy(energia = unaEnergia)
  
  def estado(unEstado: Estado): Pokemon = copy(estado = unEstado)
  
  def especie(unaEspecie: Especie): Pokemon = copy(especie = unaEspecie)

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
    especie.nuevoNivel(copy(experiencia = experiencia + unaCantidadExperiencia),unaCantidadExperiencia)

  def aumentarEnergiaAlMaximo() = energia(energiaMaxima)

  def evolucionar(): Pokemon = ??? //TODO
}