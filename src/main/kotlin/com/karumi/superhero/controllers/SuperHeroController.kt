package com.karumi.superhero.controllers

import com.karumi.superhero.domain.model.SuperHero
import com.karumi.superhero.storage.SuperHeroStorage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SuperHeroController(val superHeroStorage: SuperHeroStorage) {

  @RequestMapping("/superhero")
  fun getSuperHeroesEndpoint(
    @RequestParam(name = "name", required = false) name: String?
  ): List<SuperHero> =
    superHeroStorage.getByName(name)

  @RequestMapping("/superhero/{id}")
  fun getSuperHeroByIdEndpoint(@PathVariable("id") superHeroId: String): SuperHero =
    superHeroStorage.getById(superHeroId)

  @PostMapping("/superhero")
  fun postSuperHeroEndpoint(@RequestBody newSuperHero: SuperHero) =
    ResponseEntity(superHeroStorage.add(newSuperHero), HttpStatus.CREATED)
}