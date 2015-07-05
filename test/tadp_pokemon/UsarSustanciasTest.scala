package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class UsarSustanciasTest {  
  
  val especiePikachu = Especie(tipoPrincipal= Electrico, pesoMaximo= 100,resistenciaEvolutiva=500)
  val pikachu = Pokemon(genero=Macho,especie= especiePikachu, energia = 10)
  val beedrill = Pokemon(estado = Envenenado,energia = 100, genero=Macho,especie=Especie(tipoPrincipal= Tierra, pesoMaximo= 100,resistenciaEvolutiva=500))
  val squirtle = Pokemon(estado = KnockOut, energia = 100, genero=Hembra,especie=Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,pesoMaximo= 100,resistenciaEvolutiva=500))
  val otro = Pokemon(energia = 100,genero=Macho,especie=Especie(tipoPrincipal= Fantasma, tipoSecundario = Some(Agua) ,pesoMaximo= 100,resistenciaEvolutiva=500))

  @Test
  def usarPocionLoCura= {
        
    val trasActividad = UsarPocion(10).realizarActividad(Success(pikachu)).get
    
    assertEquals(20,trasActividad.energia)
    
  }
  
  @Test
  def usarPocionLoCuraSoloHasta50= {
        
    val trasActividad = UsarPocion(100).realizarActividad(Success(pikachu)).get
    
    assertEquals(60,trasActividad.energia)
    
  }
  
  @Test
  def usarAntidotoCuraEnvenenado= {
        
    val trasActividad = UsarAntidoto.realizarActividad(Success(beedrill)).get
    
    assertEquals(Neutro,trasActividad.estado)
    
  }
  
  @Test
  def usarAntidotoEsInocuoEnSano= {
        
    val trasActividad = UsarAntidoto.realizarActividad(Success(pikachu)).get
    
    assertEquals(Neutro,trasActividad.estado)
    
  }
  
  @Test
  def usarEtherCura= {
        
    val trasActividad = UsarAntidoto.realizarActividad(Success(beedrill)).get
    
    assertEquals(Neutro,trasActividad.estado)
    
  }
  
  @Test
  def usarEtherNoCuraKO= {
        
    val trasActividad = UsarAntidoto.realizarActividad(Success(squirtle))
    
    assert(trasActividad.isFailure)
    
  }
  
  
  
}