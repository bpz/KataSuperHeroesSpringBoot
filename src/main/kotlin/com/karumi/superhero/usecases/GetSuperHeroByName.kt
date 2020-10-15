package com.karumi.superhero.usecases

import com.karumi.superhero.data.SuperHeroStorage
import com.karumi.superhero.domain.model.SuperHero
import org.springframework.stereotype.Service

@Service
class GetSuperHeroByName(private val superHeroStorage: SuperHeroStorage) {

  operator fun invoke(name: String?): List<SuperHero> {
    val superheroes = superHeroStorage.getAll()
    return if (name != null) {
      superheroes.filter { it.name.contains(name, ignoreCase = true) }
    } else {
      superheroes
    }
  }
}