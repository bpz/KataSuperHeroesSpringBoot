package com.karumi.superhero.data

import com.karumi.superhero.controllers.model.SuperHeroModel
import com.karumi.superhero.domain.model.SuperHero
import org.springframework.stereotype.Repository

@Repository
class SuperHeroStorage(
  private val superheroes: MutableMap<String, SuperHero> =
    mutableMapOf("1" to SuperHero(id = "1", name = "Wolverine"))
) {

  private var newId = 2

  fun get(id: String): SuperHero? = superheroes[id]

  fun add(model: SuperHeroModel): SuperHero {
    val newSuperHero = SuperHero(newId.toString(), model.name, model.photo)
    superheroes[newSuperHero.id] = newSuperHero
    newId++
    return newSuperHero
  }

  fun getAll(): List<SuperHero> = superheroes.values.toList()
}