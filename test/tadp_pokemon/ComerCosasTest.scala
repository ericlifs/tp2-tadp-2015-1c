package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class ComerCosaTest {  
  
  val especiePikachu = Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(3),pesoMaximo= 100,resistenciaEvolutiva=500)
  val pikachu = Pokemon(genero=Macho,especie= especiePikachu, energia = 10)
  val beedrill = Pokemon(estado = Envenenado,energia = 100, genero=Macho,especie=Especie(tipoPrincipal= Tierra ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=500))
  val squirtle = Pokemon(estado = KnockOut, energia = 100, genero=Hembra,especie=Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=500))
  val otro = Pokemon(energia = 100,genero=Macho,especie=Especie(tipoPrincipal= Fantasma, tipoSecundario = Some(Agua) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=500))

  @Test
  def comerFeAumentaEn5Fuerza= {
        
    val trasActividad = ComerHierro.realizarActividad(Success(pikachu)).get
    
    assertEquals(6,trasActividad.fuerza)
    
  }
  
  @Test
  def comerCaAumentaEn5Velocidad= {
        
    val trasActividad = ComerCalcio.realizarActividad(Success(pikachu)).get
    
    assertEquals(6,trasActividad.velocidad)
    
  }
  
  @Test
  def comerZnAumentaEn2MaximoAtaque= {
    
    val rayito = AtaqueBase(Dragon,30)
    val impacTrueno = AtaqueBase(Dragon,30)
      
    val trasActividad = ComerZinc.realizarActividad(Success(pikachu.aprenderAtaque(rayito).aprenderAtaque(impacTrueno))).get
    
    assert(trasActividad.ataques.forall(_.puntosDeAtaqueMaximo == 32))
    
  }
  
  
}