package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class FingirIntercambioTest {  
  
  val especieRaychu = Especie(tipoPrincipal= Electrico ,pesoMaximo= 100,resistenciaEvolutiva=70, incrementoPeso=3, incrementoFuerza=3) 
  val especiePikachu = Especie(tipoPrincipal= Electrico ,evolucionador = Some(Evolucionador(CriterioIntercambiado,especieRaychu)),pesoMaximo= 100,resistenciaEvolutiva=70)
  val especieBeedrill = Especie(tipoPrincipal= Tierra ,tipoSecundario = Some(Pelea),evolucionador = Some(Evolucionador(CriterioExpuestoPiedraComun,especieRaychu)),pesoMaximo= 100,resistenciaEvolutiva=500)
  val especieSquirtle = Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,pesoMaximo= 100,resistenciaEvolutiva=500)
  val pikachu = Pokemon(genero=Macho,especie= especiePikachu )
  val raychu =  Pokemon(genero=Macho,especie= especieRaychu )
  val beedrill = Pokemon(energia = 100, genero=Macho,especie= especieBeedrill)
  val squirtle = Pokemon(peso = 20,energia = 100, genero=Hembra,especie=especieSquirtle)
  val otro = Pokemon(energia = 100,genero=Macho,especie=Especie(tipoPrincipal= Fantasma, tipoSecundario = Some(Agua),pesoMaximo= 100,resistenciaEvolutiva=500))

  
  @Test
  def evolucionaAlSerIntercambiado= {
        
    val trasActividad = FingirIntercambio.realizarActividad(Success(pikachu)).get
    
    assertEquals(especieRaychu,trasActividad.especie)    
  }
  
  @Test
  def conOtroCriterioMachoNoEvolucionaAlSerIntercambiadoYSubePeso= {
        
    val trasActividad = FingirIntercambio.realizarActividad(Success(beedrill)).get
    
    assertEquals(especieBeedrill,trasActividad.especie)  
    assertEquals(2, trasActividad.peso)
  }
  
  @Test
  def conOtroCriterioHembraNoEvolucionaAlSerIntercambiadoYSubePeso= {
        
    val trasActividad = FingirIntercambio.realizarActividad(Success(squirtle)).get
    
    assertEquals(especieSquirtle,trasActividad.especie)  
    assertEquals(10, trasActividad.peso)
  }
  
}