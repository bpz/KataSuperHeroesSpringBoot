package com.karumi.superhero.data.model

import com.karumi.superhero.domain.model.NewSuperHero
import com.karumi.superhero.domain.model.SuperHero

fun NewSuperHero.mapToSuperHeroEntity(): SuperHeroEntity =
  SuperHeroEntity(
    name = this.name,
    photo = this.photo
  )

fun SuperHeroEntity.mapToDomain() =
  SuperHero(
    id = this.id.toString(),
    name = this.name,
    photo = this.photo
  )