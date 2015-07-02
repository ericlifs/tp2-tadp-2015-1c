package tadp_pokemon


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class AtaqueTest {
  @Test
  def `puede aprender ataques de su tipo principal` = {
    val especiePikachu = new Especie
    especiePikachu.tipoPrincipal(Electrico)
    
    val rayito = new Ataque(Electrico, 10)
    
    val pikachu = new Pokemon().especie(especiePikachu).aprenderAtaque(rayito);
    
    assertEquals((rayito, 10, 10), pikachu.ataques.head)
  }
  
  @Test
  def `puede aprender ataques de su tipo secundario` = {
    val especiePikachu = new Especie
    especiePikachu.tipoPrincipal(Volador)
    especiePikachu.tipoSecundario(Electrico)
    
    val rayito = new Ataque(Electrico, 10)
    
    val pikachu = new Pokemon().especie(especiePikachu).aprenderAtaque(rayito);
    
    assertEquals((rayito, 10, 10), pikachu.ataques.head)
  }
  
  @Test
  def `no puede aprender ataques que no son de sus tipos` = {
    
    val especiePikachu = new Especie
    especiePikachu.tipoPrincipal(Volador)
    especiePikachu.tipoSecundario(Roca)
    
    val rayito = new Ataque(Electrico, 10)
    
    val pikachu = new Pokemon
    pikachu.especie(especiePikachu)
    pikachu.aprenderAtaque(rayito)
    
    assert(pikachu.ataques.isEmpty)
  }
  
  @Test
  def `atacar decrementa la cantidad de puntos de ataque` = {
    val especiePikachu = new Especie().tipoPrincipal(Electrico)
    
    val rayito = new Ataque(Electrico, 10)
    val atacarConRayito = new RealizarAtaque()
    atacarConRayito.ataque = rayito
    
    var pikachu = new Pokemon().especie(especiePikachu).aprenderAtaque(rayito)
    
    pikachu = atacarConRayito.realizarActividad(pikachu)
    assertEquals((rayito, 9, 10), pikachu.ataques.head)
    pikachu = atacarConRayito.realizarActividad(pikachu)
    assertEquals((rayito, 8, 10), pikachu.ataques.head)
  }


def `atacar aumenta sus puntos de experiencia` = {
    val especiePikachu = new Especie().tipoPrincipal(Electrico).experiencia(100)
    
    val rayito = new Ataque(Electrico, 10)
    val atacarConRayito = new RealizarAtaque()
    atacarConRayito.ataque = rayito
    
    var pikachu = new Pokemon().especie(especiePikachu).aprenderAtaque(rayito)
    
    pikachu = atacarConRayito.realizarActividad(pikachu)
  
    assertEquals(pikachu.experiencia,150);
   
  }

def `atacar con ataque del tipo secundario y es macho aumenta sus puntos de experiencia en 20 ` = {
    val especiePikachu = new Especie().genero(Macho).tipoPrincipal(volador).experiencia(100).tipoSecundario(Electrico)
    
    val rayito = new Ataque(Electrico, 10)
    val atacarConRayito = new RealizarAtaque()
    atacarConRayito.ataque = rayito
    
    var pikachu = new Pokemon().especie(especiePikachu).aprenderAtaque(rayito)
    
    pikachu = atacarConRayito.realizarActividad(pikachu)
  
    assertEquals(pikachu.experiencia,120);
   
  }

def `atacar con ataque del tipo secundario y es hembra aumenta sus puntos de experiencia en 40` = {
    val especiePikachu = new Especie().genero(Hembra).tipoPrincipal(volador).experiencia(100).tipoSecundario(Electrico)
    
    val rayito = new Ataque(Electrico, 10)
    val atacarConRayito = new RealizarAtaque()
    atacarConRayito.ataque = rayito
    
    var pikachu = new Pokemon().especie(especiePikachu).aprenderAtaque(rayito)
    
    pikachu = atacarConRayito.realizarActividad(pikachu)
  
    assertEquals(pikachu.experiencia,140);
   
  }

}