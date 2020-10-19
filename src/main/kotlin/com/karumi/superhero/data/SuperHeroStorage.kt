package com.karumi.superhero.data

import com.karumi.superhero.data.model.SuperHeroEntity
import com.karumi.superhero.domain.model.SuperHero
import org.springframework.data.repository.CrudRepository

interface SuperHeroStorage :
  CrudRepository<SuperHeroEntity, Long> {

  fun findByNameContainingIgnoreCase( name: String ): List<SuperHero>
}