package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class UsarPiedraTest {  
  
  val especieRaychu = Especie(tipoPrincipal= Electrico ,pesoMaximo= 100,resistenciaEvolutiva=70, incrementoPeso=3, incrementoFuerza=3) 
  val especiePikachu = Especie(tipoPrincipal= Electrico ,evolucionador = Some(Evolucionador(CriterioExpuestoPiedraLunar,especieRaychu)),pesoMaximo= 100,resistenciaEvolutiva=70)
  val especieBeedrill = Especie(tipoPrincipal= Tierra ,tipoSecundario = Some(Pelea),evolucionador = Some(Evolucionador(CriterioExpuestoPiedraComun,especieRaychu)),pesoMaximo= 100,resistenciaEvolutiva=500)
  val pikachu = Pokemon(genero=Macho,especie= especiePikachu )
  val raychu =  Pokemon(genero=Macho,especie= especieRaychu )
  val beedrill = Pokemon(energia = 100, genero=Macho,especie= especieBeedrill)
 
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