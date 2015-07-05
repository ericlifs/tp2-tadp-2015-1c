package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class NadarTest {  
  
  val especiePikachu = Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(3),pesoMaximo= 100,resistenciaEvolutiva=500)
  val pikachu = Pokemon(genero=Macho,especie= especiePikachu, energia = 10)
  val beedrill = Pokemon(energia = 100, genero=Macho,especie=Especie(tipoPrincipal= Tierra ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=500))
  val squirtle = Pokemon(energia = 100, genero=Hembra,especie=Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=500))
  val otro = Pokemon(energia = 100,genero=Macho,especie=Especie(tipoPrincipal= Fantasma, tipoSecundario = Some(Agua) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=500))

  
  @Test
  def pierdeEnergiaXMinutoyGana200ExperienciaAlNadar= {
        
    val trasActividad = new Nadar(5).realizarActividad(Success(pikachu)).get
    
    assertEquals(Neutro,trasActividad.estado)
    assertEquals(5,trasActividad.energia)
    assertEquals(201, trasActividad.experiencia)
    assertEquals(1, trasActividad.velocidad)
    
  }
  
  @Test
  def alNadarAguaPrincipalGana1Velocidad= {
        
    val trasActividad = new Nadar(75).realizarActividad(Success(squirtle)).get
    assertEquals(2, trasActividad.velocidad)
    
  }
  
 @Test
  def alNadarAguaSecundarioGana1Velocidad= {
        
    val trasActividad = new Nadar(75).realizarActividad(Success(otro)).get
    assertEquals(2, trasActividad.velocidad)
    
  }
 
  @Test
  def alNadarElQuePierdeConAguaQuedaKO= {
        
    val trasActividad = new Nadar(10).realizarActividad(Success(beedrill)).get
    assertEquals(KnockOut, trasActividad.estado)
    
  }
  
  
}