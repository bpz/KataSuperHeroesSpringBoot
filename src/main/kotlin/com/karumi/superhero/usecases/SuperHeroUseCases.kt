package com.karumi.superhero.usecases

import com.karumi.superhero.controllers.model.SuperHeroModel
import com.karumi.superhero.data.SuperHeroStorage
import com.karumi.superhero.domain.model.SuperHero
import org.springframework.stereotype.Service

@Service
class SuperHeroUseCases(private val superHeroStorage: SuperHeroStorage) {

  fun getSuperHeroByName(name: String?): List<SuperHero> {
    val superheroes = superHeroStorage.getAll()
    return if (name != null) {
      superheroes.filter { it.name.contains(name, ignoreCase = true) }
    } else {
      superheroes
    }
  }

  fun getSuperHero(id: String): SuperHero? = superHeroStorage.get(id)

  fun addSuperHero(model: SuperHeroModel): SuperHero = superHeroStorage.add(model)
}