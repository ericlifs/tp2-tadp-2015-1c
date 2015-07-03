package tadp_pokemon

import scala.util.Try


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class AtaqueTest {
  @Test
  def `puede aprender ataques de su tipo principal` = {
 
    val especiePikachu = new Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100)

 
    var pikachu = new Pokemon(genero=Macho,especie=especiePikachu).aprenderAtaque(new AtaqueBase(Electrico,30,null)).aumentarExperiencia(100).aprenderAtaque(new AtaqueBase(Electrico,30,null));
  
   //  assertEquals(rayito., pikachu.ataques.head) hay que mapear el ataque con ataque base
  }
  
  @Test
  def `puede aprender ataques de su tipo secundario` = {
 
 
    val especiePikachu = new Especie(tipoPrincipal= Volador ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100).tipoSecundario(Electrico);

 
    var pikachu = new Pokemon(genero=Macho,especie=especiePikachu).aprenderAtaque(new AtaqueBase(Electrico,30,null)).aumentarExperiencia(100).aprenderAtaque(new AtaqueBase(Electrico,30,null));
  
   //  assertEquals(rayito., pikachu.ataques.head) hay que mapear el ataque con ataque base
  }
  
  @Test
  def `no puede aprender ataques que no son de sus tipos` = {
    val especiePikachu = new Especie(tipoPrincipal= Volador,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100)

 
    var pikachu = new Pokemon(genero=Macho,especie=especiePikachu).aprenderAtaque(new AtaqueBase(Electrico,30,null)).aumentarExperiencia(100).aprenderAtaque(new AtaqueBase(Electrico,30,null));
  
    assert(pikachu.ataques.isEmpty)
  }
  
  @Test
  def `atacar decrementa la cantidad de puntos de ataque` = {

    
    val especiePikachu = new Especie(tipoPrincipal= Electrico,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100)
    
    val rayito = new Ataque(new AtaqueBase(Electrico,30,null))
    val atacarConRayito = new RealizarAtaque(new AtaqueBase(Electrico,30,null))
    
    var pikachu = new Pokemon(genero=Macho,especie=especiePikachu).aprenderAtaque(new AtaqueBase(Electrico,30,null)).aumentarExperiencia(100);
    
    val trypikachu: Try[Pokemon] =  atacarConRayito.realizarActividad(Try(pikachu))
    
   // assertEquals((rayito, 8, 10), pikachu.ataques.head)  hay que mapear el ataque con ataque base
  
  
  
    }
  


def `atacar aumenta sus puntos de experiencia` = {
  
  
  val especiePikachu = new Especie(tipoPrincipal= Electrico,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100)
    
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