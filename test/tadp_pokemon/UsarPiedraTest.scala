package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class UsarPiedraTest {  
  
  val especieRaychu = Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(3),pesoMaximo= 100,resistenciaEvolutiva=70, incrementoPeso=3, incrementoFuerza=3) 
  val especiePikachu = Especie(tipoPrincipal= Electrico ,criterioEvolucion=CriterioExpuestoPiedraLunar,pesoMaximo= 100,resistenciaEvolutiva=70, especieCualEvoluciona = Some(especieRaychu))
  val especieBeedrill = Especie(tipoPrincipal= Tierra ,tipoSecundario = Some(Pelea),criterioEvolucion=CriterioExpuestoPiedraComun,pesoMaximo= 100,resistenciaEvolutiva=500,especieCualEvoluciona = Some(especieRaychu) )
  val pikachu = Pokemon(genero=Macho,especie= especiePikachu )
  val raychu =  Pokemon(genero=Macho,especie= especieRaychu )
  val beedrill = Pokemon(energia = 100, genero=Macho,especie= especieBeedrill)
  val squirtle = Pokemon(energia = 100, genero=Hembra,especie=Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=500))
  val otro = Pokemon(energia = 100,genero=Macho,especie=Especie(tipoPrincipal= Fantasma, tipoSecundario = Some(Agua) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=500))

  
  @Test
  def evolucionaAlUSarPiedraLunar= {
        
    val trasActividad = new UsarPiedra(PiedraLunar).realizarActividad(Success(pikachu)).get
    
    assertEquals(especieRaychu,trasActividad.especie)    
  }
  
  @Test
  def evolucionaAlUSarPiedraDeSuTipo= {
        
    val trasActividad = new UsarPiedra(PiedraComun(Tierra)).realizarActividad(Success(beedrill)).get
    
    assertEquals(especieRaychu,trasActividad.especie)    
  }
  
  @Test
  def noEvolucionaAlUSarPiedraDeSuTipoCuandoTieneCriterioLunar= {
        
    val trasActividad = new UsarPiedra(PiedraComun(Electrico)).realizarActividad(Success(pikachu)).get
    
    assertEquals(especiePikachu,trasActividad.especie)    
  }
  
  @Test
  def noEvolucionaYQuedaNormalAlUSarPiedraDeTipoInferior= {
        
    val trasActividad = new UsarPiedra(PiedraComun(Electrico)).realizarActividad(Success(beedrill)).get
    
    assertEquals(especieBeedrill,trasActividad.especie)    
    assertEquals(Neutro,trasActividad.estado)
  }
  
  @Test
  def noEvolucionaYSeEnvenenaAlUSarPiedraDeTipoSuperiorAlPrincipal= {
        
    val trasActividad = new UsarPiedra(PiedraComun(Agua)).realizarActividad(Success(beedrill)).get
    
    assertEquals(especieBeedrill,trasActividad.especie)    
    assertEquals(Envenenado,trasActividad.estado)
  }
  
  @Test
  def noEvolucionaYSeEnvenenaAlUSarPiedraDeTipoSuperiorAlSecundario= {
        
    val trasActividad = new UsarPiedra(PiedraComun(Psiquico)).realizarActividad(Success(beedrill)).get
    
    assertEquals(especieBeedrill,trasActividad.especie)    
    assertEquals(Envenenado,trasActividad.estado)
  }
  
  @Test
  def noEvolucionaAlUSarPiedraLunarSiNoEsSuCriterio= {
        
    val trasActividad = new UsarPiedra(PiedraLunar).realizarActividad(Success(beedrill)).get
    
    assertEquals(especieBeedrill,trasActividad.especie)    
  }
  
  
  
}