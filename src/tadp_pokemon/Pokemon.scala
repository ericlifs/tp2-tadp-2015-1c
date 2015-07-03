package tadp_pokemon

import scala.util.{Try,Success,Failure}

object Pokemon{
  
  def apply(
    nivel: Int, experiencia: Int,  genero: Genero,  energia: Int , 
    energiaMaxima: Int ,  peso: Int ,  fuerza: Int ,  velocidad: Int , 
    estado: Estado,  especie: Especie,  ataques: List[Ataque]
    ) : Pokemon =
    new Pokemon(nivel, experiencia, genero, energia, energiaMaxima, peso, fuerza, velocidad,
        estado, especie, ataques)
  
  def unapply(pokemon:Pokemon): Option[(TipoPokemon,Option[TipoPokemon],Genero,Estado)] = 
    Some((pokemon.especie.tipoPrincipal,pokemon.especie.tipoSecundario,pokemon.genero,pokemon.estado))  
    
}

class Pokemon(
    val nivel: Int = 0, val experiencia: Int = 0, val genero: Genero, val energia: Int = 0, 
    val energiaMaxima: Int = 0, val peso: Int = 0, val fuerza: Int = 0, val velocidad: Int = 0, 
    val estado: Estado = Neutro, val especie: Especie, val ataques: List[Ataque] = List()
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

  def ataques(unosAtaques: List[AtaqueBase]): Pokemon = {
    unosAtaques.foldLeft(this){(pokemon, ataqueBase) => pokemon.aprenderAtaque(ataqueBase)}
  }

  def aprenderAtaque(ataqueBase: AtaqueBase): Pokemon = 
    copy(ataques = ataques:+new Ataque(ataqueBase))

  def foreachAtaque(funcion: (Ataque => Unit)) ={
      ataques.foreach(funcion)
      this      
    }   
    
  def aumentarExperiencia(unaCantidadExperiencia: Int) = 
    especie.nuevoNivel(copy(experiencia = experiencia + unaCantidadExperiencia),unaCantidadExperiencia)

  def aumentarEnergiaAlMaximo() = energia(energiaMaxima)
      
  def evolucionarSiDebeTras(actividad: Actividad): Pokemon = 
    especie.evolucionarSiDebeTras(actividad,this)
  
  def debeEvolucionarTras(actividad: Actividad): Boolean =
    especie.debeEvolucionarTras(actividad,this)

  def esValido() = ??? //TODO
  
  def hacerActividad(actividad: Actividad) =
    estado match{
      case KnockOut => Failure(new Exception("Pokemon estaba KnockOut"))
      case Dormido => Success(this)
      case _ => actividad.afectarSiPuede(Success(this))
    }
  
  def esPrincipalmenteDe(tipo: TipoPokemon): Boolean =
    especie.esPrincipalmenteDe(tipo)
    
  def copy(
    nivel: Int = nivel, experiencia: Int = experiencia,  genero: Genero = genero,  energia: Int = energia, 
    energiaMaxima: Int = energiaMaxima ,  peso: Int = peso,  fuerza: Int = fuerza,  velocidad: Int = velocidad, 
    estado: Estado = estado,  especie: Especie = especie,  ataques: List[Ataque] = ataques
    ) =
    new Pokemon(nivel, experiencia, genero, energia, energiaMaxima, peso, fuerza, velocidad,
        estado, especie, ataques)

}


