package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class CambioNivel {  
  
  var pikachu:Pokemon = _
  var beedrill : Pokemon= _
  var otro:Pokemon= _
  var squirtle:Pokemon= _
  var especieRaychu:Especie = _
  var especiePikachu: Especie = _
  
  def setUp() = {
    
    especieRaychu = Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=70, incrementoPeso=3, incrementoFuerza=3) 
    especiePikachu = Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(3),pesoMaximo= 100,resistenciaEvolutiva=70, especieCualEvoluciona = Some(especieRaychu))
    pikachu = Pokemon(genero=Macho,especie= especiePikachu )
    beedrill = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Dragon ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
    squirtle = Pokemon(genero=Hembra,especie=Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
    otro = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Planta, tipoSecundario = Some(Electrico) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
  }
  
  @Test
  def subeANivel2YSubeCaracteristicas= {
 
    setUp
    
    val rayito = AtaqueBase(Dragon,30)
        
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(Success(pikachu.aprenderAtaque(rayito))).get
    
    assertEquals(2,trasAtacar.nivel)
    assertEquals(2,trasAtacar.energiaMaxima)
    assertEquals(2,trasAtacar.peso)
    assertEquals(2,trasAtacar.fuerza)
    assertEquals(2,trasAtacar.velocidad)
    assertEquals(especiePikachu,trasAtacar.especie)
   
  }
  
  @Test
  def noSubeANivel2YQuedaIgual= {
 
    setUp
    
    val rayito = AtaqueBase(Electrico,30)
        
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(Success(pikachu.aprenderAtaque(rayito))).get
    
    assertEquals(1,trasAtacar.nivel)
    assertEquals(1,trasAtacar.nivel)  
    assertEquals(1,trasAtacar.nivel)
    assertEquals(1,trasAtacar.energiaMaxima)
    assertEquals(1,trasAtacar.peso)
    assertEquals(1,trasAtacar.fuerza)
    assertEquals(1,trasAtacar.velocidad)
    assertEquals(especiePikachu,trasAtacar.especie)  
   
  }
  
   @Test
  def noSubeANivel3PeroSiAl2YSubeCaracteristicas= {
 
    setUp
    
    val rayito = AtaqueBase(Electrico,30)
        
    var trasAtacarTry = new RealizarAtaque(rayito).realizarActividad(Success(pikachu.aprenderAtaque(rayito)))
    
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(trasAtacarTry).get
    
    assertEquals(2,trasAtacar.nivel)  
    assertEquals(2,trasAtacar.nivel)
    assertEquals(2,trasAtacar.energiaMaxima)
    assertEquals(2,trasAtacar.peso)
    assertEquals(2,trasAtacar.fuerza)
    assertEquals(2,trasAtacar.velocidad)
    assertEquals(especiePikachu,trasAtacar.especie)  
  }
   
  @Test
  def subeANivel3EvolucionaYSubeCaracteristicasSegunNuevaEspecie= {
 
    setUp
    
    val rayito = AtaqueBase(Dragon,30)
        
    var trasAtacarTry = new RealizarAtaque(rayito).realizarActividad(Success(pikachu.aprenderAtaque(rayito)))
    
    var trasAtacarTry2 = new RealizarAtaque(rayito).realizarActividad(trasAtacarTry)
    
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(trasAtacarTry2).get
    
    assertEquals(3,trasAtacar.nivel)
    assertEquals(3,trasAtacar.energiaMaxima)
    assertEquals(5,trasAtacar.peso)
    assertEquals(5,trasAtacar.fuerza)
    assertEquals(3,trasAtacar.velocidad)
    assertEquals(especieRaychu,trasAtacar.especie)
    
  }
     
}