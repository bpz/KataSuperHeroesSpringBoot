package com.karumi.superhero.usecases

import com.karumi.superhero.data.SuperHeroStorage
import com.karumi.superhero.domain.model.SuperHero
import org.springframework.stereotype.Service

@Service
class GetSuperHeroById(private val superHeroStorage: SuperHeroStorage) {

  operator fun invoke(id: String): SuperHero? = superHeroStorage.get(id)
}