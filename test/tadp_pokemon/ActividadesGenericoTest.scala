package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class ActividadesGenericoTest {  
  
  implicit class FComposition[A, B](f: A => B) {
    def <<[C](g: C => A): C => B = f.compose(g)
  }
  
  val especiePikachu = Especie(tipoPrincipal= Electrico ,pesoMaximo= 100,resistenciaEvolutiva=500)
  val pikachu = Pokemon(genero=Macho,especie= especiePikachu, energia = 10, estado = Dormido())
  val beedrill = Pokemon(estado = Envenenado,energia = 100, genero=Macho,especie=Especie(tipoPrincipal= Tierra,pesoMaximo= 100,resistenciaEvolutiva=500))
  val squirtle = Pokemon(estado = KnockOut, energia = 100, genero=Hembra,especie=Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,pesoMaximo= 100,resistenciaEvolutiva=500))
  val otro = Pokemon(energia = 10,genero=Macho,especie=Especie(tipoPrincipal= Fantasma, tipoSecundario = Some(Agua) ,pesoMaximo= 100,resistenciaEvolutiva=500))

  @Test
  def siEstaKONoPuedeHacerNada= {
        
    val trasActividad = ComerHierro.realizarActividad(Success(squirtle))
    
    assert(trasActividad.isFailure)
    
  }
  
  @Test
  def siEstaDormidoNoHaceNada3veces= {
        
    val trasActividad = 
    (ComerHierro.realizarActividad _ <<
    ComerHierro.realizarActividad _ <<
    ComerHierro.realizarActividad _) (Success(pikachu)).get
    
    
    assertEquals(1,trasActividad.fuerza)
    
  }
  
  @Test
  def siEstaDormidoNoHaceNada3vecesYALa4taSi= {
        
    val trasActividad = 
    (ComerHierro.realizarActividad _ <<
    ComerHierro.realizarActividad _ <<
    ComerHierro.realizarActividad _ <<
    ComerHierro.realizarActividad _) (Success(pikachu)).get
    
    
    assertEquals(6,trasActividad.fuerza)
    
  }
  
  @Test
  def siQuedaInvalidoTrasActividadFalla= {
        
    val trasActividad = new Nadar(50).realizarActividad(Success(otro))
    
    assert(trasActividad.isFailure)
    
  }
 
 
  
}