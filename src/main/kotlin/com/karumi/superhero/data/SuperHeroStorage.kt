package com.karumi.superhero.data

import com.karumi.superhero.controllers.model.SuperHeroModel
import com.karumi.superhero.domain.model.SuperHero
import org.springframework.stereotype.Component

@Component
class SuperHeroStorage(
  private val superheroes: MutableMap<String, SuperHero> =
    mutableMapOf("1" to SuperHero(id = "1", name = "Wolverine"))
) {

  private var newId = 1

  fun getSuperHero(id: String): SuperHero? = superheroes[id]

  fun addSuperHero(model: SuperHeroModel): SuperHero {
    val newSuperHero = SuperHero(newId.toString(), model.name, model.photo)
    superheroes[newSuperHero.id] = newSuperHero
    newId++
    return newSuperHero
  }

  fun getAll(): List<SuperHero> = superheroes.values.toList()
}