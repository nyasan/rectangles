package com.nuvelance.rectangles.service

import com.nuvelance.rectangles.entity.Rectangle
import com.nuvelance.rectangles.repository.RectangleRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class RectangleServiceTest {

    private val rectangleRepository: RectangleRepository = mockk()
    private val rectangleService: RectangleService = RectangleService(rectangleRepository)

    @Test
    fun `Get rectangles`() {
        every { rectangleRepository.findAll() } returns arrayListOf(Rectangle(1, 0, 0, 200, 100))

        val result = rectangleService.getAll()

        assertEquals(1, result[0].id)
        assertEquals(200, result[0].width)
    }

    @Test
    fun `Get a rectangle`() {
        every { rectangleRepository.findById(1).get() } returns Rectangle(1, 0, 0, 100, 200)

        val result = rectangleService.getOne(1)

        assertEquals(1, result.id)
        assertEquals(100, result.width)
    }

    @Test
    fun `Create a rectangle`() {
        every { rectangleRepository.save(any()) } returns Rectangle(1, 0, 0, 100, 200)

        val result = rectangleService.createRectangle(0, 0, 100, 200)

        assertEquals(1, result.id)
        assertEquals(100, result.width)
    }

    @Test
    fun `Update a rectangle`() {
        val returnRectangle = Rectangle(1, 100, 100, 400, 400)
        every { rectangleRepository.findById(1).get() } returns returnRectangle
        every { rectangleRepository.save(any()) } returns returnRectangle

        val result = rectangleService.updateRectangle(1, Optional.of(100), Optional.of(100),Optional.of(400),Optional.of(400))

        verify(exactly = 1) {
            rectangleRepository.findById(1).get()
            rectangleRepository.save(returnRectangle)
        }
        assertEquals(1, result.id)
        assertEquals(400, result.width)
    }
}