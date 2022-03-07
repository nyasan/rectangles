package com.nuvelance.rectangles.controller

import com.nuvelance.rectangles.entity.Rectangle
import com.nuvelance.rectangles.service.ComparisonService
import com.nuvelance.rectangles.util.AdjacentType
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
internal class ComparisonControllerTest{
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var comparisonService: ComparisonService

    @Test
    fun `Get is contained`() {
        val rectangles = Pair(Rectangle(1, 0, 0, 200, 150), Rectangle(2, 50, 50, 100, 200))
        Mockito.`when`(
            comparisonService.getRectangles(1,2)
        ).thenReturn(rectangles)
        Mockito.`when`(
            comparisonService.isContained(rectangles = rectangles)
        ).thenReturn(true)


        mockMvc.perform(MockMvcRequestBuilders.get("/comparison/isContained?innerRectangleId=2&outerRectangleId=1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `Get is adjacent`() {
        val rectangles = Pair(Rectangle(1, 0, 0, 200, 150), Rectangle(2, 50, 50, 100, 200))
        Mockito.`when`(
            comparisonService.getRectangles(1,2)
        ).thenReturn(rectangles)
        Mockito.`when`(
            comparisonService.isAdjacent(rectangles = rectangles)
        ).thenReturn(AdjacentType.PROPER)


        mockMvc.perform(MockMvcRequestBuilders.get("/comparison/isAdjacent?firstRectangle=1&secondRectangle=2"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `Get is is overlapping`() {
        val rectangles = Pair(Rectangle(1, 0, 0, 200, 150), Rectangle(2, 50, 50, 100, 200))
        Mockito.`when`(
            comparisonService.getRectangles(1,2)
        ).thenReturn(rectangles)
        Mockito.`when`(
            comparisonService.isOverlapping(rectangles = rectangles)
        ).thenReturn("The intersection coordinates are: (100,120) (60,170)")


        mockMvc.perform(MockMvcRequestBuilders.get("/comparison/isOverlapping?firstRectangle=1&secondRectangle=2"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }
}