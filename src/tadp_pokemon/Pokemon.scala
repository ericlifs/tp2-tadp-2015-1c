package tadp_pokemon

import scala.util.{Try,Success,Failure}

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
  
  def aumentarPeso(unPeso: Int): Pokemon = copy(peso = peso + unPeso)
  
  def aumentarEnergia(unaCantidad: Int): Pokemon = energia((energia+unaCantidad).min(energiaMaxima))

  def ataques(unosAtaques: List[Ataque]): Pokemon = {
    unosAtaques.foldLeft(this){(pokemon, ataque) => pokemon.aprenderAtaque(ataque)}
  }

  def aprenderAtaque(unAtaque: Ataque): Pokemon = 
    if(especie.esAfin(unAtaque)) copy(ataques = ataques:+unAtaque) else this

  def foreachAtaque(f: (Ataque => Ataque)) =
    ataques(ataques.map(f))
    
  def aumentarExperiencia(unaCantidadExperiencia: Int) = 
    especie.nuevoNivel(copy(experiencia = experiencia + unaCantidadExperiencia),unaCantidadExperiencia)

  def aumentarEnergiaAlMaximo() = energia(energiaMaxima)
  
  def estaCansado() = 
    energia < 0.5*energiaMaxima
    
  def dormirse() : Pokemon =
    if(estaCansado() && estado == Neutro) dormirse() else this
    
  def quedarseDormido()=
    estado(Dormido)

  def evolucionar(): Pokemon = ??? //TODO
    
}

class Misteriosa{
  
  def intentarActividad(pokemon: Try[Pokemon], actividad: Actividad): Try[Pokemon] = //TODO despues hacer bien
    actividad.realizarActividadSiPuede(pokemon) //TODO a discutir qué polimorfismo usar
  
}