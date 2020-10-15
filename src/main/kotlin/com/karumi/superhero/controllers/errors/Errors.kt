package com.karumi.superhero.controllers.errors

import com.karumi.superhero.domain.model.SuperHero
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
object NotFound : ResponseEntity<SuperHero>(HttpStatus.NOT_FOUND)