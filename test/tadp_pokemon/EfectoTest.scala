package tadp_pokemon

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class EfectoTest {
  
  @Test
  def `el efecto reposar sube la energía al máximo y lo deja dormido` = {
    val especiePikachu = new Especie
    especiePikachu.tipoPrincipal = Electrico
    
    val rayito = new Ataque(Electrico, 10, Reposar)
    val atacarConRayito = new RealizarAtaque()
    atacarConRayito.ataque = rayito
    
    var pikachu = new Pokemon
    pikachu.especie(especiePikachu)
    pikachu.energiaMaxima(100)
    pikachu.aprenderAtaque(rayito)
    
    pikachu = atacarConRayito.realizarActividad(pikachu)
    
    assertEquals(100, pikachu.energia)
    assertEquals(Dormido, pikachu.estado)
  }
  
  @Test
  def `el efecto enfocarse sube la velocidad un punto` = {
    val especiePikachu = new Especie
    especiePikachu.tipoPrincipal = Electrico
    
    val rayito = new Ataque(Electrico, 10, Enfocarse)
    val atacarConRayito = new RealizarAtaque()
    atacarConRayito.ataque = rayito
    
    var pikachu = new Pokemon
    pikachu.especie(especiePikachu)
    pikachu.velocidad(10)
    pikachu.aprenderAtaque(rayito)
    
    val mew = new Pokemon
    pikachu = atacarConRayito.realizarActividad(pikachu)
    
    assertEquals(11, pikachu.velocidad)
  }
  
  @Test
  def `el efecto endurecerse sube la energía en 5 puntos y lo paraliza` = {
    val especiePikachu = new Especie
    especiePikachu.tipoPrincipal = Electrico
    
    val rayito = new Ataque(Electrico, 10, Endurecerse)
    val atacarConRayito = new RealizarAtaque()
    atacarConRayito.ataque = rayito
    
    var pikachu = new Pokemon
    pikachu.especie(especiePikachu)
    pikachu.energia(100)
    pikachu.aprenderAtaque(rayito)
    
    pikachu = atacarConRayito.realizarActividad(pikachu)
    
    assertEquals(105, pikachu.energia)
    assertEquals(Paralizado, pikachu.estado)
  }
}