package tadp_pokemon

//Este factory esta hecho para solucionar el Code Smell de Long Parameter List que tendría
//si le pasara todos los valores al momento de instanciar al Pokemon. Al momento de crearlo
//voy a fijarme si los atributos cuentan con un valor para saber si puedo hacerlo o no.
class PokemonFactory {
  
  var nivel: Int = _
  
  var experiencia: Int = _
  
  var genero: String = _
  
  var energia: Int = _
  
  var energiaMaxima: Int = _
  
  var peso: Int = _
  
  var fuerza: Int = _
  
  var velocidad: Int = _
  
  var estado: Estado = _
  
  var ataques: List[Ataque] = _
  
  var especie: Especie = _
  
  def nivel(unNivel: Int) {
    if (1 <= unNivel && unNivel <= 100) {
      nivel = unNivel
    } else {
      this.raiseFieldValueException("nivel")
    }
  }
  
  def experiencia(unaExperiencia: Int) {
    experiencia = unaExperiencia
  }
  
  def genero(unGenero: String) {
    if (unGenero == "Macho" || unGenero == "Hembra") {
      genero = unGenero
    } else {
      this.raiseFieldValueException("genero")
    }
  }
  
  def energia(unaEnergia: Int) {
    energia = unaEnergia
  }
  
  def energiaMaxima(unaEnergiaMaxima: Int) {
    energiaMaxima = unaEnergiaMaxima
  }
  
  def peso(unPeso: Int) {
    if (unPeso >= 0) {
      peso = unPeso
    } else {
      this.raiseFieldValueException("peso")
    }
  }
  
  def fuerza(unaFuerza: Int) {
    if (1 <= unaFuerza && unaFuerza <= 100) {
      fuerza = unaFuerza
    } else {
      this.raiseFieldValueException("fuerza")
    }
  }
  
  def velocidad(unaVelocidad: Int) {
    if (1 <= unaVelocidad && unaVelocidad <= 100) {
      velocidad = unaVelocidad
    } else {
      this.raiseFieldValueException("velocidad")
    }
  }
  
  def estado(unEstado: Estado) {
    estado = unEstado
  }
  
  def especie(unaEspecie: Especie) {
    especie = unaEspecie
  }
  
  def agregarAtaque(unAtaque: Ataque) {
    if (unAtaque.tipo == this.especie.tipoPrincipal || (this.especie.tipoSecundario != null && unAtaque.tipo == this.especie.tipoSecundario))
      ataques = ataques.::(unAtaque)
  }
  
  def construirPokemon() : Pokemon = {
      
    def pokemon = new Pokemon()
    
    pokemon.nivel(this.nivel)
    pokemon.experiencia(this.experiencia)
    pokemon.genero(this.genero)
    pokemon.energia(this.energia)
    pokemon.energiaMaxima(this.energiaMaxima)
    pokemon.peso(this.peso)
    pokemon.fuerza(this.fuerza)
    pokemon.velocidad(this.velocidad)
    pokemon.estado(this.estado)
    pokemon.ataques(this.ataques)
    pokemon.especie(this.especie)
    
    return pokemon
  }
  
  def raiseFieldValueException(field: String) {
    throw new Exception("El valor informado para el campo " + field + " no es válido.")
  }
}