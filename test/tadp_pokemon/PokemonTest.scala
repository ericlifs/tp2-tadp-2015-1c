package tadp_pokemon

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class PokemonTest {
  
  var pikachu:Pokemon = _
  
  @Test
  def `respeta energia maxima` = {
    pikachu = Pokemon(energia=100, energiaMaxima=150, genero=Macho,especie=Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))

    pikachu = pikachu.aumentarEnergia(20)
    assertEquals(120, pikachu.energia)
    pikachu = pikachu.aumentarEnergia(100)
    assertEquals(150, pikachu.energia)
  }
}