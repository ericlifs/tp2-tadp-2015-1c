package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class RutinaTest {  
  
  implicit class FComposition[A, B](f: A => B) {
    def °[C](g: C => A): C => B = f.compose(g)
    def <<[C](g: C => A): C => B = f.compose(g)
  }
  
  val especieRaychu = Especie(tipoPrincipal= Electrico,pesoMaximo= 100,resistenciaEvolutiva=70, incrementoPeso=3, incrementoFuerza=3) 
  val especiePikachu = Especie(tipoPrincipal= Electrico ,evolucionador = Some(Evolucionador(CriterioSubirNivel(3),especieRaychu)),pesoMaximo= 100,resistenciaEvolutiva=70)
  val pikachu = Pokemon(energia=10,fuerza=10,genero=Macho,especie= especiePikachu )
  val raychu =  Pokemon(genero=Macho,especie= especieRaychu )
  val especieBeedrill = Especie(tipoPrincipal= Tierra ,tipoSecundario = Some(Pelea),evolucionador = Some(Evolucionador(CriterioExpuestoPiedraComun,especieRaychu)),pesoMaximo= 100,resistenciaEvolutiva=500)
  val especieSquirtle = Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,pesoMaximo= 100,resistenciaEvolutiva=500)
  val beedrill = Pokemon(energia = 100, genero=Macho,especie= especieBeedrill)
  val squirtle = Pokemon(peso = 20,energia = 100, genero=Hembra,especie=especieSquirtle)
  val otro = Pokemon(energia = 100,genero=Macho,especie=Especie(tipoPrincipal= Fantasma, tipoSecundario = Some(Agua) ,pesoMaximo= 100,resistenciaEvolutiva=500))

  val masFuerte: (Pokemon, Pokemon) => Boolean = _.fuerza > _.fuerza
  val rutinaComerHierro3 = RutinaComun(List(ComerHierro,ComerHierro,ComerHierro))
  val rutinaComerHierro2 = RutinaComun(List(ComerHierro,ComerHierro))
  val rutinaNoAptaParaFantasmas = RutinaComun(List(ComerHierro,new LevantarPesas(10),ComerHierro))
  val rutinaNoAptaParaFantasmas2 = RutinaComun(List(new LevantarPesas(10),ComerHierro,Descansar))
  
  
  @Test
  def haceRutinaConExito= {
    
    val trasAtacar = RutinaComun(List(ComerHierro,ComerHierro)).realizar(pikachu).get

    assertEquals(20,trasAtacar.fuerza)
   
  }
  
  @Test
  def siEnELMedioQuedaInvalidoNoSigue= {
    
    val trasAtacar = RutinaComun(List(ComerHierro,new Nadar(50),ComerHierro)).realizar(pikachu)

    assert(trasAtacar.isFailure)
   
  }
  
  @Test
  def siEnELMedioQuedaKONoSigue= {
    
    val trasAtacar = RutinaComun(List(ComerHierro,new Nadar(1),ComerHierro)).realizar(beedrill)

    assert(trasAtacar.isFailure)
   
  }
  
  @Test
  def evolucionaYSubeNivelYCaracteristicasAMediaRutina= {
    
    val rayito = AtaqueBase(Dragon,30)
    
    val trasAtacar = 
    RutinaComun(List(RealizarAtaque(rayito), RealizarAtaque(rayito), RealizarAtaque(rayito))).realizar(pikachu.aprenderAtaque(rayito)).get
    
    assertEquals(3,trasAtacar.nivel)
    assertEquals(102,trasAtacar.energiaMaxima)
    assertEquals(5,trasAtacar.peso)
    assertEquals(14,trasAtacar.fuerza)
    assertEquals(3,trasAtacar.velocidad)
    assertEquals(especieRaychu,trasAtacar.especie)
   
  }  
  
  @Test
  def siEnMedioAlgoNoPuedeNoSigue= {
    
    val trasAtacar = rutinaNoAptaParaFantasmas.realizar(otro)

    assert(trasAtacar.isFailure)
   
  }
  
  @Test
  def elegirMejorRutinaPorFuerza= {
    
    val mejorRutina = pikachu.analizarRutinas(List(rutinaComerHierro2,rutinaComerHierro3), masFuerte.curried).get

    assertEquals(rutinaComerHierro3, mejorRutina)
   
  }
  
  @Test
  def siRutinaFallaSoloEligeEntreLAsQueQuedan= {
    
    val mejorRutina = otro.analizarRutinas(List(rutinaNoAptaParaFantasmas,rutinaComerHierro2), masFuerte.curried).get

    assertEquals(rutinaComerHierro2, mejorRutina)
   
  }
  
  @Test
  def siNoPuedeNingunaDevuelveNone= {
    
    val mejorRutina = otro.analizarRutinas(List(rutinaNoAptaParaFantasmas,rutinaNoAptaParaFantasmas2), masFuerte.curried)

    assert(mejorRutina.isEmpty)
   
  }
  
  
}