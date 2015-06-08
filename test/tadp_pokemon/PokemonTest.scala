package tadp_pokemon

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class PokemonTest {
  @Test
  def `respeta energia maxima` = {
    val pikachu = new Pokemon
    pikachu.energia = 100
    pikachu.energiaMaxima = 150
    pikachu.aumentarEnergia(20)
    assertEquals("pikachu", pikachu.energia, 120)
    pikachu.aumentarEnergia(100)
    assertEquals("pikachu", pikachu.energia, 150)
  }
}