package com.karumi.superhero.controllers

import com.karumi.superhero.controllers.exceptions.NotFound
import com.karumi.superhero.controllers.model.SuperHeroModel
import com.karumi.superhero.domain.model.SuperHero
import com.karumi.superhero.usecases.SuperHeroUseCases
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SuperHeroController(
  val superHeroUseCases: SuperHeroUseCases
) {

  @RequestMapping("/superhero")
  fun getSuperHeroesEndpoint(
    @RequestParam(name = "name", required = false) name: String?):
    List<SuperHero> = superHeroUseCases.getSuperHeroByName(name)


  @RequestMapping("/superhero/{id}")
  fun getSuperHeroByIdEndpoint(@PathVariable("id") superHeroId: String): SuperHero =
    superHeroUseCases.getSuperHero(superHeroId) ?: throw NotFound

  @PostMapping("/superhero")
  fun postSuperHeroEndpoint(@RequestBody newSuperHeroModel: SuperHeroModel) =
    ResponseEntity(superHeroUseCases.addSuperHero(newSuperHeroModel), HttpStatus.CREATED)
}