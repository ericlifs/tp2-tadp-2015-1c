package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class CambioNivel {  
  
  implicit class FComposition[A, B](f: A => B) {
    def °[C](g: C => A): C => B = f.compose(g)
    def <<[C](g: C => A): C => B = f.compose(g)
  }
  
  val especieRaychu = Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(3),pesoMaximo= 100,resistenciaEvolutiva=70, incrementoPeso=3, incrementoFuerza=3) 
  val especiePikachu = Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(3),pesoMaximo= 100,resistenciaEvolutiva=70, especieCualEvoluciona = Some(especieRaychu))
  val pikachu = Pokemon(genero=Macho,especie= especiePikachu )
  val raychu =  Pokemon(genero=Macho,especie= especieRaychu )
  
  @Test
  def subeANivel2YSubeCaracteristicas= {
    val rayito = AtaqueBase(Dragon,30)
        
    val trasAtacar = RealizarAtaque(rayito).realizarActividad(Success(pikachu.aprenderAtaque(rayito))).get
    
    assertEquals(2,trasAtacar.nivel)
    assertEquals(101,trasAtacar.energiaMaxima)
    assertEquals(2,trasAtacar.peso)
    assertEquals(2,trasAtacar.fuerza)
    assertEquals(2,trasAtacar.velocidad)
    assertEquals(especiePikachu,trasAtacar.especie)
   
  }
  
  @Test
  def noSubeANivel2YQuedaIgual= {
    val rayito = AtaqueBase(Electrico,30)
        
    val trasAtacar = RealizarAtaque(rayito).realizarActividad(Success(pikachu.aprenderAtaque(rayito))).get
    
    assertEquals(1,trasAtacar.nivel)
    assertEquals(1,trasAtacar.nivel)  
    assertEquals(1,trasAtacar.nivel)
    assertEquals(100,trasAtacar.energiaMaxima)
    assertEquals(1,trasAtacar.peso)
    assertEquals(1,trasAtacar.fuerza)
    assertEquals(1,trasAtacar.velocidad)
    assertEquals(especiePikachu,trasAtacar.especie)  
   
  }
  
   @Test
  def noSubeANivel3PeroSiAl2YSubeCaracteristicas= {
    val rayito = AtaqueBase(Electrico,30)
    
    val actividades = (RealizarAtaque(rayito).realizarActividad _ << RealizarAtaque(rayito).realizarActividad _)     
    val trasAtacar = actividades (Success(pikachu.aprenderAtaque(rayito))).get
    
    assertEquals(2,trasAtacar.nivel)  
    assertEquals(2,trasAtacar.nivel)
    assertEquals(101,trasAtacar.energiaMaxima)
    assertEquals(2,trasAtacar.peso)
    assertEquals(2,trasAtacar.fuerza)
    assertEquals(2,trasAtacar.velocidad)
    assertEquals(especiePikachu,trasAtacar.especie)  
  }
   
  @Test
  def subeANivel3EvolucionaYSubeCaracteristicasSegunNuevaEspecie= {
    
    val rayito = AtaqueBase(Dragon,30)
    
    val trasAtacar = 
    (RealizarAtaque(rayito).realizarActividad _ <<
    RealizarAtaque(rayito).realizarActividad _ <<
    RealizarAtaque(rayito).realizarActividad _) (Success(pikachu.aprenderAtaque(rayito))).get
    
    assertEquals(3,trasAtacar.nivel)
    assertEquals(102,trasAtacar.energiaMaxima)
    assertEquals(5,trasAtacar.peso)
    assertEquals(5,trasAtacar.fuerza)
    assertEquals(3,trasAtacar.velocidad)
    assertEquals(especieRaychu,trasAtacar.especie)
    
  }
  
  @Test
  def subeANivel3QuedaIgualPorqueNoTieneEvolucion= {
    
    val rayito = AtaqueBase(Dragon,30)
    
    val trasAtacar = 
    (RealizarAtaque(rayito).realizarActividad _ <<
    RealizarAtaque(rayito).realizarActividad _ <<
    RealizarAtaque(rayito).realizarActividad _) (Success(raychu.aprenderAtaque(rayito))).get
    
    assertEquals(3,trasAtacar.nivel)
    assertEquals(especieRaychu,trasAtacar.especie)
    
  }
     
}