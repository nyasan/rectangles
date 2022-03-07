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
import java.util.*

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
                    "    \"x\": 0,\n" +
                    "    \"y\": 0,\n" +
                    "    \"width\": 100,\n" +
                    "    \"height\": 200\n" +
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
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].width", Matchers.`is`(100)))
    }

    @Test
    fun `Get rectangle`() {
        val str =
            "{\n" +
                    "    \"id\": 1,\n" +
                    "    \"x\": 20,\n" +
                    "    \"y\": 20,\n" +
                    "    \"width\": 10,\n" +
                    "    \"height\": 10\n" +
                    "  }"
        val objectMapper = ObjectMapper()
        val rectangleResponses: Rectangle =
            objectMapper.readValue(str, object : TypeReference<Rectangle>() {})
        Mockito.`when`(
            rectangleService.getOne(1)
        ).thenReturn(rectangleResponses)

        mockMvc.perform(MockMvcRequestBuilders.get("/rectangle/id/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.width", Matchers.`is`(10)))
    }

    @Test
    fun `POST a rectangle`() {
        val str =
            "{\n" +
                    "    \"id\": 1,\n" +
                    "    \"x\": 0,\n" +
                    "    \"y\": 0,\n" +
                    "    \"width\": 100,\n" +
                    "    \"height\": 200\n" +
                    "  }"
        val objectMapper = ObjectMapper()
        val rectangleResponses: Rectangle =
            objectMapper.readValue(str, object : TypeReference<Rectangle>() {})
        Mockito.`when`(
            rectangleService.createRectangle(0, 0, 100, 200)
        ).thenReturn(rectangleResponses)

        mockMvc.perform(MockMvcRequestBuilders.post("/rectangle?x=0&y=0&width=100&height=200"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.width", Matchers.`is`(100)))
    }

    @Test
    fun `PUT a rectangle`() {
        val str =
            "{\n" +
                    "    \"id\": 1,\n" +
                    "    \"x\": 0,\n" +
                    "    \"y\": 0,\n" +
                    "    \"width\": 100,\n" +
                    "    \"height\": 200\n" +
                    "  }"
        val objectMapper = ObjectMapper()
        val rectangleResponses: Rectangle =
            objectMapper.readValue(str, object : TypeReference<Rectangle>() {})
        Mockito.`when`(
            rectangleService.getOne(1)
        ).thenReturn(rectangleResponses)
        Mockito.`when`(
            rectangleService.updateRectangle(1, Optional.of(0), Optional.of(0), Optional.of(100), Optional.of(200))
        ).thenReturn(rectangleResponses)

        mockMvc.perform(MockMvcRequestBuilders.put("/rectangle?id=1&x=0&y=0&width=100&height=200"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.width", Matchers.`is`(100)))
    }
}