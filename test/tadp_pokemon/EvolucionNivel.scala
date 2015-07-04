package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class EvolucionNivel {  
  
  var pikachu:Pokemon = _
  var beedrill : Pokemon= _
  var otro:Pokemon= _
  var squirtle:Pokemon= _
  
  def setUp() = {
    
    pikachu = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Electrico ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
    beedrill = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Dragon ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
    squirtle = Pokemon(genero=Hembra,especie=Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
    otro = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Planta, tipoSecundario = Some(Electrico) ,criterioEvolucion=new CriterioSubirNivel(100),pesoMaximo= 100,resistenciaEvolutiva=100))
  }
  
  @Test
  def noPuedeSINotienePuntos = {
 
    setUp
    
    val rayito = AtaqueBase(Dragon,0)
        
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(Success(beedrill.aprenderAtaque(rayito)))
    
    assert(trasAtacar.isFailure)
   
   
  }
   
}