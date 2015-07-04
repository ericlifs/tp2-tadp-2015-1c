package tadp_pokemon

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class PokemonTest {
  
  var pikachu:Pokemon
  
  @Test
  def `respeta energia maxima` = {
    pikachu = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))

    pikachu.aumentarEnergia(20)
    assertEquals(120, pikachu.energia)
    pikachu.aumentarEnergia(100)
    assertEquals(150, pikachu.energia)
  }
}