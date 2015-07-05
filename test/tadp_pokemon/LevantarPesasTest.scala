package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class LevantarPesasTest {  
  
  val especieRaychu = Especie(tipoPrincipal= Electrico ,pesoMaximo= 100,resistenciaEvolutiva=70, incrementoPeso=3, incrementoFuerza=3) 
  val especiePikachu = Especie(tipoPrincipal= Electrico ,pesoMaximo= 100,resistenciaEvolutiva=70,evolucionador =(Some(Evolucionador(CriterioSubirNivel(3),especieRaychu))))
  val pikachu = Pokemon(genero=Macho,especie= especiePikachu )
  val pikachuParalizado = Pokemon(genero=Macho,especie= especiePikachu, estado = Paralizado)
  val beedrill = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Pelea ,pesoMaximo= 100,resistenciaEvolutiva=100))
  val squirtle = Pokemon(genero=Hembra,especie=Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,pesoMaximo= 100,resistenciaEvolutiva=100))
  val otro = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Fantasma, tipoSecundario = Some(Electrico) ,pesoMaximo= 100,resistenciaEvolutiva=100))

  
  @Test
  def levantaPesasYGanaPuntos= {
        
    val trasActividad = new LevantarPesas(5).realizarActividad(Success(pikachu)).get
    
    assertEquals(6,trasActividad.experiencia)
    
  }
  
  @Test
  def levantaPesasPeleaSecundarioYGanaDoblesPuntos= {
        
    val trasActividad = new LevantarPesas(5).realizarActividad(Success(squirtle)).get
    
    assertEquals(11,trasActividad.experiencia)
    
  }
  
  @Test
  def levantaPesasPeleaPrincipalYGanaDoblesPuntos= {
        
    val trasActividad = new LevantarPesas(5).realizarActividad(Success(beedrill)).get
    
    assertEquals(11,trasActividad.experiencia)
    
  }
  
  @Test
  def fantasmaNoPuedeLevantarPesas = {
        
    val trasActividad = new LevantarPesas(5).realizarActividad(Success(otro))
    
    assert(trasActividad.isFailure)
    
  }
  
  @Test
  def levantaPesasMuyPesadasYSeParaliza = {
        
    val trasActividad = new LevantarPesas(12).realizarActividad(Success(pikachu)).get
    
    assertEquals(1,trasActividad.experiencia)
    assertEquals(Paralizado,trasActividad.estado)
    
  }
  
  @Test
  def levantaPesasParalizado = {
        
    val trasActividad = new LevantarPesas(12).realizarActividad(Success(pikachuParalizado)).get
    
    assertEquals(1,trasActividad.experiencia)
    assertEquals(KnockOut,trasActividad.estado)
    
  }
  
}