package com.karumi.superhero.usecases

import com.karumi.superhero.controllers.model.SuperHeroModel
import com.karumi.superhero.data.SuperHeroStorage
import com.karumi.superhero.domain.model.SuperHero
import org.springframework.stereotype.Service

@Service
class AddSuperHero(private val superHeroStorage: SuperHeroStorage) {

  operator fun invoke(model: SuperHeroModel): SuperHero = superHeroStorage.add(model)
}