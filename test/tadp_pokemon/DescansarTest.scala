package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class DescansarTest {  
  
  val especiePikachu = Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(3),pesoMaximo= 100,resistenciaEvolutiva=500)
  val pikachu = Pokemon(genero=Macho,especie= especiePikachu, energia = 100,ataques = List(new Ataque(AtaqueBase(Dragon,30)),new Ataque(AtaqueBase(Dragon,30))))

  @Test
  def descansaYRecuperaPuntosDeAtaqueYSigueDespierto= {

      
    val trasActividad = Descansar.realizarActividad(Success(pikachu.foreachAtaque(_.perderPuntos(5)))).get
    
    assert(trasActividad.ataques.forall(_.puntosDeAtaqueMaximo == 30))
    assertEquals(Neutro, trasActividad.estado)
    
  }  
  
  @Test
  def descansaYRecuperaPuntosDeAtaqueYSeDuerme= {
      
    val trasActividad = Descansar.realizarActividad(Success(pikachu.energia(40).foreachAtaque(_.perderPuntos(5)))).get
    
    assert(trasActividad.ataques.forall(_.puntosDeAtaqueMaximo == 30))
    assertEquals(Dormido(0), trasActividad.estado)
    
  }
  
}