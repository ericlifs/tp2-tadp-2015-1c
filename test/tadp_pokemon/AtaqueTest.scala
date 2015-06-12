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
    
    val pikachu = new Pokemon
    pikachu.especie(especiePikachu)
    pikachu.aprenderAtaque(rayito)
    
    assertEquals((rayito, 10, 10), pikachu.ataques.head)
  }
  
  @Test
  def `puede aprender ataques de su tipo secundario` = {
    val especiePikachu = new Especie
    especiePikachu.tipoPrincipal(Volador)
    especiePikachu.tipoSecundario(Electrico)
    
    val rayito = new Ataque(Electrico, 10)
    
    val pikachu = new Pokemon
    pikachu.especie(especiePikachu)
    pikachu.aprenderAtaque(rayito)
    
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
    val especiePikachu = new Especie
    especiePikachu.tipoPrincipal(Electrico)
    
    val rayito = new Ataque(Electrico, 10)
    
    val pikachu = new Pokemon
    pikachu.especie(especiePikachu)
    pikachu.aprenderAtaque(rayito)
    
    val mew = new Pokemon
    
    pikachu.atacarA(mew, rayito)
    assertEquals((rayito, 9, 10), pikachu.ataques.head)
    pikachu.atacarA(mew, rayito)
    assertEquals((rayito, 8, 10), pikachu.ataques.head)
  }
}