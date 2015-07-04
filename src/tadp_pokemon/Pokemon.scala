package tadp_pokemon

import scala.util.{Try,Success,Failure}

object Pokemon{
  
  def apply(
    nivel: Int = 1,  experiencia: Int = 1,  genero: Genero,  energia: Int = 1, 
     energiaMaxima: Int = 1,  peso: Int = 1,  fuerza: Int = 1,  velocidad: Int = 1, 
     estado: Estado = Neutro,  especie: Especie,  ataques: List[Ataque] = List()
    ) : Pokemon =
    new Pokemon(nivel, experiencia, genero, energia, energiaMaxima, peso, fuerza, velocidad,
        estado, especie, ataques)
  
  def unapply(pokemon:Pokemon): Option[(TipoPokemon,Option[TipoPokemon],Genero,Estado)] = 
    Some((pokemon.especie.tipoPrincipal,pokemon.especie.tipoSecundario,pokemon.genero,pokemon.estado))  
    
}

class Pokemon(
    val nivel: Int = 1, val experiencia: Int = 1, val genero: Genero, val energia: Int = 1, 
    val energiaMaxima: Int = 1, val peso: Int = 1, val fuerza: Int = 1, val velocidad: Int = 1, 
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
  
  def aumentarEnergia(unaCantidad: Int): Pokemon = copy(energia = (energia+unaCantidad).min(energiaMaxima))

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
    copy(experiencia = experiencia + unaCantidadExperiencia)
    
  def incrementarNivel()= copy(nivel = nivel+1)

  def aumentarEnergiaAlMaximo() = energia(energiaMaxima)
  
  def aumentarCaracteristicas(incrementoEnergiaMaxima: Int, incrementoPeso: Int, incrementoFuerza: Int, incrementoVelocidad: Int): Pokemon = 
    aumentarEnergiaMaxima(incrementoEnergiaMaxima)
    .aumentarPeso(incrementoPeso)
    .aumentarFuerza(incrementoFuerza)
    .aumentarVelocidad(incrementoVelocidad)
      
  def efectosPosterioresActividad(actividad: Actividad): Pokemon = 
    especie.efectosPosterioresActividad(actividad,this)
  
  def debeEvolucionarTras(actividad: Actividad): Boolean =
    especie.debeEvolucionarTras(actividad,this)
    
  def puedeSubirNivel():Boolean = 
    experiencia >= especie.experienciaNecesariaProximoNivel(nivel)

  def esValido() =
    entre(nivel,1,100)&&entre(fuerza,1,100)&&entre(velocidad,1,100)&&peso<=especie.pesoMaximo
    
  def entre (numero: Int,minimo: Int,maximo: Int): Boolean = numero <= maximo && numero >= minimo  
  
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

def analizarRutinas(rutinas: List[Rutina], condicion: (Try[Pokemon] => Int)): String = {
    var pokemon = Success(this)
    rutinas.sortWith((rut1, rut2) => condicion(rut1.realizar(pokemon)) < condicion(rut2.realizar(pokemon))).head.nombre
  }  

}
