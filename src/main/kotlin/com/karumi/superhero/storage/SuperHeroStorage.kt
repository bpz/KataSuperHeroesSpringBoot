package com.karumi.superhero.storage

import com.karumi.superhero.domain.model.SuperHero
import org.springframework.stereotype.Component

@Component
class SuperHeroStorage {
  private final val defaultSuperHero: SuperHero = SuperHero(id = "1", name = "Wolverine")
  var superHeroes: MutableList<SuperHero> = mutableListOf(defaultSuperHero)

  fun getByName(name: String?): List<SuperHero> {
    return if (name != null) {
      superHeroes.filter { it.name.contains(name, ignoreCase = true) }
    } else {
      listOf(defaultSuperHero)
    }
  }

  fun getById(superHeroId: String): SuperHero? =
    superHeroes.find { it.id == superHeroId }

  fun add(newSuperHero: SuperHero): SuperHero {
    superHeroes.add(newSuperHero)
    return newSuperHero
  }
}