package com.karumi.superhero.controllers

import com.karumi.superhero.controllers.exceptions.NotFound
import com.karumi.superhero.controllers.model.SuperHeroModel
import com.karumi.superhero.domain.model.SuperHero
import com.karumi.superhero.usecases.AddSuperHero
import com.karumi.superhero.usecases.GetSuperHeroById
import com.karumi.superhero.usecases.GetSuperHeroByName
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.* // ktlint-disable no-wildcard-imports

@RestController
class SuperHeroController(
  val addSuperHero: AddSuperHero,
  val getSuperHeroByName: GetSuperHeroByName,
  val getSuperHeroById: GetSuperHeroById
) {

  @RequestMapping("/superhero")
  fun getSuperHeroesEndpoint(
    @RequestParam(name = "name", required = false) name: String?
  ):
    List<SuperHero> = getSuperHeroByName(name)

  @RequestMapping("/superhero/{id}")
  fun getSuperHeroByIdEndpoint(@PathVariable("id") superHeroId: String): SuperHero =
    getSuperHeroById(superHeroId) ?: throw NotFound

  @PostMapping("/superhero")
  fun postSuperHeroEndpoint(@RequestBody newSuperHeroModel: SuperHeroModel) =
    ResponseEntity(addSuperHero(newSuperHeroModel), HttpStatus.CREATED)
}