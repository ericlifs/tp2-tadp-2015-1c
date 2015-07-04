package tadp_pokemon

import scala.util.{Try,Success,Failure}

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class EfectoTest {
  
   var pikachu:Pokemon
  
  @Test
  def `el efecto reposar sube la energía al máximo y lo deja dormido` = {
    val especiePikachu = new Especie(tipoPrincipal=Electrico,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100).tipoSecundario(Volador)
    
    val rayito = new Ataque(new AtaqueBase(Electrico,10,null))
    val atacarConRayito = new RealizarAtaque(new AtaqueBase(Electrico,30,null))
    
    pikachu = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
    pikachu.aprenderAtaque(new AtaqueBase(Electrico,10,null))
    
    val trypikachu: Try[Pokemon] =  atacarConRayito.realizarActividad(Try(pikachu))
    
    assertEquals(100, pikachu.energia)
    assertEquals(Dormido, pikachu.estado)
  }
  
  @Test
  def `el efecto enfocarse sube la velocidad un punto` = {
    val especiePikachu = new Especie(tipoPrincipal=Electrico,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100).tipoSecundario(Volador)
    
    val rayito = new Ataque(new AtaqueBase(Electrico,10,null))
    val atacarConRayito = new RealizarAtaque(new AtaqueBase(Electrico,30,null))
    
    pikachu = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
    pikachu.aprenderAtaque(new AtaqueBase(Electrico,10,null))

    val trypikachu: Try[Pokemon] =  atacarConRayito.realizarActividad(Try(pikachu))
    
    assertEquals(11, pikachu.velocidad)
  }
  
  @Test
  def `el efecto endurecerse sube la energía en 5 puntos y lo paraliza` = {
    val especiePikachu = new Especie(tipoPrincipal=Electrico,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100).tipoSecundario(Volador)
    
    val rayito = new Ataque(new AtaqueBase(Electrico,10,null))
    val atacarConRayito = new RealizarAtaque(new AtaqueBase(Electrico,30,null))
    
    pikachu = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
    pikachu.aprenderAtaque(new AtaqueBase(Electrico,10,null))
    
    val trypikachu: Try[Pokemon] =  atacarConRayito.realizarActividad(Try(pikachu))
    
    assertEquals(105, pikachu.energia)
    assertEquals(Paralizado, pikachu.estado)
  }
}