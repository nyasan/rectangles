package com.nuvelance.rectangles.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nuvelance.rectangles.entity.Rectangle
import com.nuvelance.rectangles.service.RectangleService
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
internal class RectangleControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var rectangleService: RectangleService

    @Test
    fun `Get all rectangles`() {
        val str =
            "[{\n" +
                    "    \"id\": 1,\n" +
                    "    \"side1\": 20,\n" +
                    "    \"side2\": 20,\n" +
                    "    \"side3\": 10,\n" +
                    "    \"side4\": 10\n" +
                    "  }]"
        val objectMapper = ObjectMapper()
        val rectangleResponses: List<Rectangle> =
            objectMapper.readValue(str, object : TypeReference<List<Rectangle>>() {})
        Mockito.`when`(
            rectangleService.getAll()
        ).thenReturn(rectangleResponses)

        mockMvc.perform(MockMvcRequestBuilders.get("/rectangle/all"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize<Any>(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].side1", Matchers.`is`(20)))
    }

    @Test
    fun `Get rectangle`() {
        val str =
            "{\n" +
                    "    \"id\": 1,\n" +
                    "    \"side1\": 20,\n" +
                    "    \"side2\": 20,\n" +
                    "    \"side3\": 10,\n" +
                    "    \"side4\": 10\n" +
                    "  }"
        val objectMapper = ObjectMapper()
        val rectangleResponses: Rectangle =
            objectMapper.readValue(str, object : TypeReference<Rectangle>() {})
        Mockito.`when`(
            rectangleService.getOne(1)
        ).thenReturn(rectangleResponses)

        mockMvc.perform(MockMvcRequestBuilders.get("/rectangle/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.side1", Matchers.`is`(20)))
    }

    @Test
    fun `POST a rectangle`() {
        val str =
            "{\n" +
                    "    \"id\": 1,\n" +
                    "    \"side1\": 10,\n" +
                    "    \"side2\": 10,\n" +
                    "    \"side3\": 20,\n" +
                    "    \"side4\": 20\n" +
                    "  }"
        val objectMapper = ObjectMapper()
        val rectangleResponses: Rectangle =
            objectMapper.readValue(str, object : TypeReference<Rectangle>() {})
        Mockito.`when`(
            rectangleService.createRectangle(10, 20)
        ).thenReturn(rectangleResponses)

        mockMvc.perform(MockMvcRequestBuilders.post("/rectangle?sides12=10&sides34=20"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.side1", Matchers.`is`(10)))
    }

    @Test
    fun `PUT a rectangle`() {
        val str =
            "{\n" +
                    "    \"id\": 1,\n" +
                    "    \"side1\": 10,\n" +
                    "    \"side2\": 20,\n" +
                    "    \"side3\": 10,\n" +
                    "    \"side4\": 10\n" +
                    "  }"
        val objectMapper = ObjectMapper()
        val rectangleResponses: Rectangle =
            objectMapper.readValue(str, object : TypeReference<Rectangle>() {})
        Mockito.`when`(
            rectangleService.getOne(1)
        ).thenReturn(rectangleResponses)
        Mockito.`when`(
            rectangleService.updateRectangle(1, 10, 20)
        ).thenReturn(rectangleResponses)

        mockMvc.perform(MockMvcRequestBuilders.put("/rectangle?id=1&sides12=10&sides34=20"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.side1", Matchers.`is`(10)))
    }
}