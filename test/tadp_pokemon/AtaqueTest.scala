package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class AtaqueTest {
  
  var pikachu,beedrill,otro,squirtle:Pokemon
    
  override def setUp = {
    
    pikachu = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
    beedrill = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Volador ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
    squirtle = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
    otro = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Planta, tipoSecundario = Some(Electrico) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
  }
  
  
  @Test
  def `puede aprender ataques de su tipo principal` = {  
   assert(AprenderAtaque(AtaqueBase(Electrico,30)).realizarActividad(Success(pikachu)).get.ataques.size == 1)  
  }
  
  @Test
  def `puede aprender ataques de su tipo secundario` = {
    assert(AprenderAtaque(AtaqueBase(Electrico,30)).realizarActividad(Success(otro)).get.ataques.size == 1)
  }
  
  @Test
  def `no puede aprender ataques que no son de sus tipos` = {    
    assert(AprenderAtaque(AtaqueBase(Electrico,30)).realizarActividad(Success(squirtle)).get.ataques.isEmpty,"Aprende aunque no sea de sus dos tipos")
    assert(AprenderAtaque(AtaqueBase(Electrico,30)).realizarActividad(Success(beedrill)).get.ataques.isEmpty,"Aprende aunque no sea de suprincipal sin tener secundario")
  }
    
  @Test
  def `atacar decrementa la cantidad de puntos de ataque` = {
    
    val rayito = new Ataque(new AtaqueBase(Electrico,30,null))
    val atacarConRayito = new RealizarAtaque(new AtaqueBase(Electrico,30,null))
    
    var pikachu = new Pokemon(genero=Macho,especie=especiePikachu).aprenderAtaque(new AtaqueBase(Electrico,30,null)).aumentarExperiencia(100);
    
    val trypikachu: Try[Pokemon] =  atacarConRayito.realizarActividad(Try(pikachu))
    
   // assertEquals((rayito, 8, 10), pikachu.ataques.head)  hay que mapear el ataque con ataque base
  
  
  
    }
  


def `atacar aumenta sus puntos de experiencia` = {
 
    val rayito = new Ataque(new AtaqueBase(Electrico,30,null))
    val atacarConRayito = new RealizarAtaque(new AtaqueBase(Electrico,30,null))
    
    var pikachu = new Pokemon(genero=Macho,especie=especiePikachu).aprenderAtaque(new AtaqueBase(Electrico,30,null)).aumentarExperiencia(100);
    
    val trypikachu: Try[Pokemon] =  atacarConRayito.realizarActividad(Try(pikachu))
  
    assertEquals(trypikachu.get.experiencia,150);
   
  }
   

def `atacar con ataque del tipo secundario y es macho aumenta sus puntos de experiencia en 20 ` = {
    val especiePikachu = new Especie(tipoPrincipal= Volador,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100).tipoSecundario(Electrico)
    
    val rayito = new Ataque(new AtaqueBase(Electrico,30,null))
    val atacarConRayito = new RealizarAtaque(new AtaqueBase(Electrico,30,null))
    
    var pikachu = new Pokemon(genero=Macho,especie=especiePikachu).aprenderAtaque(new AtaqueBase(Electrico,30,null)).aumentarExperiencia(100);
    
    val trypikachu: Try[Pokemon] =  atacarConRayito.realizarActividad(Try(pikachu))
  
    assertEquals(trypikachu.get.experiencia,120);
   
  }

def `atacar con ataque del tipo secundario y es hembra aumenta sus puntos de experiencia en 40` = {
    val especiePikachu = new Especie(tipoPrincipal= Volador,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100).tipoSecundario(Electrico)
    
    val rayito = new Ataque(new AtaqueBase(Electrico,30,null))
    val atacarConRayito = new RealizarAtaque(new AtaqueBase(Electrico,30,null))
    
    var pikachu = new Pokemon(genero=Hembra,especie=especiePikachu).aprenderAtaque(new AtaqueBase(Electrico,30,null)).aumentarExperiencia(100);
    
    val trypikachu: Try[Pokemon] = atacarConRayito.realizarActividad(Try(pikachu))
  
    assertEquals(trypikachu.get.experiencia,140);
   
  }
}