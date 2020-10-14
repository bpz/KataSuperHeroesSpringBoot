package com.karumi.superhero.storage

import com.karumi.superhero.domain.model.SuperHero
import org.springframework.stereotype.Component

@Component
class SuperHeroStorage {

  var superHeroes: MutableList<SuperHero> = mutableListOf(SuperHero(id = "1", name = "Wolverine"))
  val defaultSuperHero: SuperHero = SuperHero(id = "1", name = "Wolverine")

  fun getByName(name: String?): List<SuperHero> {
    return if (name != null) {
      superHeroes.filter { it.name.contains(name, ignoreCase = true) }
    } else {
      listOf(defaultSuperHero)
    }
  }

  fun getById(superHeroId: String): SuperHero =
    superHeroes.find { it.id == superHeroId } ?: defaultSuperHero

  fun add(newSuperHero: SuperHero): SuperHero {
    superHeroes.add(newSuperHero)
    return newSuperHero
  }
}