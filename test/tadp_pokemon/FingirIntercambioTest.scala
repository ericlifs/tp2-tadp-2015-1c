package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class FingirIntercambioTest {  
  
  val especieRaychu = Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(3),pesoMaximo= 100,resistenciaEvolutiva=70, incrementoPeso=3, incrementoFuerza=3) 
  val especiePikachu = Especie(tipoPrincipal= Electrico ,criterioEvolucion=CriterioIntercambiado,pesoMaximo= 100,resistenciaEvolutiva=70, especieCualEvoluciona = Some(especieRaychu))
  val especieBeedrill = Especie(tipoPrincipal= Tierra ,tipoSecundario = Some(Pelea),criterioEvolucion=CriterioExpuestoPiedraComun,pesoMaximo= 100,resistenciaEvolutiva=500,especieCualEvoluciona = Some(especieRaychu) )
  val especieSquirtle = Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=500)
  val pikachu = Pokemon(genero=Macho,especie= especiePikachu )
  val raychu =  Pokemon(genero=Macho,especie= especieRaychu )
  val beedrill = Pokemon(energia = 100, genero=Macho,especie= especieBeedrill)
  val squirtle = Pokemon(peso = 20,energia = 100, genero=Hembra,especie=especieSquirtle)
  val otro = Pokemon(energia = 100,genero=Macho,especie=Especie(tipoPrincipal= Fantasma, tipoSecundario = Some(Agua) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=500))

  
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