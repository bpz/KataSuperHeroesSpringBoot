package com.karumi.superhero.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.karumi.superhero.controllers.model.SuperHeroModel
import com.karumi.superhero.data.SuperHeroStorage
import com.karumi.superhero.domain.model.SuperHero
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.Before
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SuperHeroControllerTest(
  @Autowired val mockMvc: MockMvc
) {

  @MockkBean
  lateinit var superHeroStorage: SuperHeroStorage

  val ANY_SUPERHERO = SuperHero(id = "1", name = "Wolverine")
  val NEW_SUPERHERO_MODEL: SuperHeroModel = SuperHeroModel("Cyclops")
  val NEW_SUPERHERO: SuperHero = SuperHero("2", "Cyclops")
  val WRONG_NEW_SUPERHERO_MODEL = "{}"

  @Before
  fun resetStorage() {
    superHeroStorage.reset()
  }

  @Test
  fun `should return the list of superheroes when contains superheroes`() {
    every { superHeroStorage.getAll() } returns listOf(ANY_SUPERHERO)

    mockMvc.perform(MockMvcRequestBuilders
      .get("/superhero"))

      .andExpect(status().isOk)
      .andExpect(content().json((listOf(ANY_SUPERHERO).toJson()), true))
  }

  @Test
  fun `should return the list of superheroes filters by name`() {
    every { superHeroStorage.getAll() } returns listOf(ANY_SUPERHERO)

    mockMvc.perform(MockMvcRequestBuilders
      .get("/superhero?name=wol"))

      .andExpect(status().isOk)
      .andExpect(content().json((listOf(ANY_SUPERHERO).toJson()), true))
  }

  @Test
  fun `should return a superhero if the id exist`() {
    every { superHeroStorage.get(ANY_SUPERHERO.id) } returns ANY_SUPERHERO

    mockMvc.perform(MockMvcRequestBuilders
      .get("/superhero/1"))

      .andExpect(status().isOk)
      .andExpect(content().json(ANY_SUPERHERO.toJson(), true))
  }

  @Test
  fun `should return the superhero created if the values are correct`() {
    every { superHeroStorage.add(any()) } returns NEW_SUPERHERO

    mockMvc.perform(MockMvcRequestBuilders
      .post("/superhero")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(NEW_SUPERHERO_MODEL.toJson()))

      .andExpect(status().isCreated)
      .andExpect(content().json(NEW_SUPERHERO.toJson(), true))
  }

  @Test
  fun `should return an error if the new superhero is invalid`() {
    mockMvc.perform(MockMvcRequestBuilders
      .post("/superhero")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(WRONG_NEW_SUPERHERO_MODEL))

      .andExpect(status().isUnprocessableEntity)
  }
}

private fun <T> T.toJson(): String {
  val objectMapper = ObjectMapper()
  return objectMapper.writeValueAsString(this)
}