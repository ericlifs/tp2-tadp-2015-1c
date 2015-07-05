package tadp_pokemon

import scala.util.{Try,Success,Failure}


import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Ignore

class AtaqueTest {  
  
  var pikachu:Pokemon = _
  var beedrill : Pokemon= _
  var otro:Pokemon= _
  var squirtle:Pokemon= _
  
  def setUp() = {
    
    pikachu = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Electrico ,pesoMaximo= 100,resistenciaEvolutiva=100))
    beedrill = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Dragon ,pesoMaximo= 100,resistenciaEvolutiva=100))
    squirtle = Pokemon(genero=Hembra,especie=Especie(tipoPrincipal= Agua, tipoSecundario = Some(Pelea) ,pesoMaximo= 100,resistenciaEvolutiva=100))
    otro = Pokemon(genero=Macho,especie=Especie(tipoPrincipal= Planta, tipoSecundario = Some(Electrico) ,pesoMaximo= 100,resistenciaEvolutiva=100))
  }
  
  
  @Test
  def `puede aprender ataques de su tipo principal` = {  
    setUp
   assert(AprenderAtaque(AtaqueBase(Electrico,30)).realizarActividad(Success(pikachu)).get.ataques.size == 1)  
  }
  
  @Test
  def `puede aprender ataques de su tipo secundario` = {
    setUp
    assert(AprenderAtaque(AtaqueBase(Electrico,30)).realizarActividad(Success(otro)).get.ataques.size == 1)
  }
  
    @Test
  def `puede aprender ataques de tipo Normal` = {
    setUp
    assert(AprenderAtaque(AtaqueBase(Normal,30)).realizarActividad(Success(otro)).get.ataques.size == 1)
  }
  
  @Test
  def `no puede aprender ataques que no son de sus 2 tipos` = {    
    setUp
    var trasAprender = AprenderAtaque(AtaqueBase(Electrico,30)).realizarActividad(Success(squirtle)).get
    assert(trasAprender.ataques.isEmpty)
    assert(trasAprender.estado == KnockOut)

  }
  
  @Test
  def `no puede aprender ataques que no son de su unico tipo` = {    
    setUp
    var trasAprender = AprenderAtaque(AtaqueBase(Electrico,30)).realizarActividad(Success(beedrill)).get
    assert(AprenderAtaque(AtaqueBase(Electrico,30)).realizarActividad(Success(beedrill)).get.ataques.isEmpty)
    assert(trasAprender.estado == KnockOut)

  }
  
      
    
  @Test
  def `atacar decrementa la cantidad de puntos de ataque` = {
    
    setUp
    
    val rayito = AtaqueBase(Electrico,30)
        
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(Success(pikachu.aprenderAtaque(rayito))).get
    
    assertEquals(trasAtacar.ataques.find(_.esBasicamente(rayito)).get.puntosDeAtaque,29)
   
  
  }
  
 @Test
  def `Atacar con tipo principal aumenta exp en 50` = {
 
    setUp
    
    val rayito = AtaqueBase(Electrico,30)
        
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(Success(pikachu.aprenderAtaque(rayito))).get
    
    assertEquals(trasAtacar.experiencia,51)
   
   
  }
 
  @Test
  def `Atacar macho con tipo secundario aumenta exp en 20` = {
 
    setUp
    
    val rayito = AtaqueBase(Electrico,30)
        
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(Success(otro.aprenderAtaque(rayito))).get
    
    assertEquals(trasAtacar.experiencia,21)
   
   
  }
  
    @Test
  def `Atacar hermbra con tipo secundario aumenta exp en 40` = {
 
    setUp
    
    val rayito = AtaqueBase(Pelea,30)
        
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(Success(squirtle.aprenderAtaque(rayito))).get
    
    assertEquals(41,trasAtacar.experiencia)
   
   
  }
    
  @Test
  def `Atacar Dragon siempre aumenta exp en 80` = {
 
    setUp
    
    val rayito = AtaqueBase(Dragon,30)
        
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(Success(beedrill.aprenderAtaque(rayito))).get
    
    assertEquals(81,trasAtacar.experiencia)
   
   
  }
  
  @Test
  def alAtacarSufreEfectoSecundario = {
 
    setUp
    
    val rayito = AtaqueBase(Dragon,30, {pokemon => pokemon.aumentarFuerza(99)})
        
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(Success(beedrill.aprenderAtaque(rayito))).get
    
    assertEquals(100,trasAtacar.fuerza)
   
   
  }
  
  @Test
  def noPuedeSINoConoceAtaque = {
 
    setUp
    
    val rayito = AtaqueBase(Dragon,30)
        
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(Success(beedrill))
    
    assert(trasAtacar.isFailure)
   
   
  }
  
  @Test
  def noPuedeSINotienePuntos = {
 
    setUp
    
    val rayito = AtaqueBase(Dragon,0)
        
    var trasAtacar = new RealizarAtaque(rayito).realizarActividad(Success(beedrill.aprenderAtaque(rayito)))
    
    assert(trasAtacar.isFailure)
   
   
  }
   
}