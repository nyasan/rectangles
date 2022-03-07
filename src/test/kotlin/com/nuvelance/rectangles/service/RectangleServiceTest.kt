package com.nuvelance.rectangles.service

import com.nuvelance.rectangles.entity.Rectangle
import com.nuvelance.rectangles.repository.RectangleRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RectangleServiceTest {

    private val rectangleRepository: RectangleRepository = mockk()
    private val rectangleService: RectangleService = RectangleService(rectangleRepository)

    @Test
    fun `Get rectangles`() {
        every { rectangleRepository.findAll() } returns arrayListOf(Rectangle(1, 10, 10, 20, 20))

        val result = rectangleService.getAll()

        assertEquals(1, result[0].id)
        assertEquals(20, result[0].side3)
    }

    @Test
    fun `Get a rectangle`() {
        every { rectangleRepository.findById(1).get() } returns Rectangle(1, 10, 10, 20, 20)

        val result = rectangleService.getOne(1)

        assertEquals(1, result.id)
        assertEquals(20, result.side3)
    }

    @Test
    fun `Create a rectangle`() {
        every { rectangleRepository.save(any()) } returns Rectangle(1, 10, 10, 20, 20)

        val result = rectangleService.createRectangle(10, 20)

        assertEquals(1, result.id)
        assertEquals(20, result.side3)
    }

    @Test
    fun `Update a rectangle`() {
        val returnRectangle = Rectangle(1, 10, 10, 20, 20)
        every { rectangleRepository.findById(1).get() } returns returnRectangle
        every { rectangleRepository.save(any()) } returns returnRectangle

        val result = rectangleService.updateRectangle(1, 10, 20)

        verify(exactly = 1) {
            rectangleRepository.findById(1).get()
            rectangleRepository.save(returnRectangle)
        }
        assertEquals(1, result.id)
        assertEquals(10, result.side3)
    }
}