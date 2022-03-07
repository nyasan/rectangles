package com.nuvelance.rectangles.service

import com.nuvelance.rectangles.entity.Rectangle
import com.nuvelance.rectangles.util.AdjacentType
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class ComparisonServiceTest{

    private val rectangleService: RectangleService = mockk()

    private val comparisonService: ComparisonService = ComparisonService(rectangleService)

    @Test
    fun `Check if a rectangle is not contained in another`() {
        every { rectangleService.getOne(1) } returns constructRectangle1()
        every { rectangleService.getOne(2) } returns constructRectangle2()
        val rectangles = comparisonService.getRectangles(1, 2)
        val result = comparisonService.isContained(rectangles)

        assertEquals(false, result)
    }

    @Test
    fun `Check if a rectangle is contained in another`() {
        every { rectangleService.getOne(1) } returns constructRectangle1()
        every { rectangleService.getOne(3) } returns constructRectangle3()
        val rectangles = comparisonService.getRectangles(1, 3)
        val result = comparisonService.isContained(rectangles)

        assertEquals(true, result)
    }

    @Test
    fun `Check if a rectangle hasn't intersections with another`() {
        every { rectangleService.getOne(1) } returns constructRectangle1()
        every { rectangleService.getOne(3) } returns constructRectangle3()
        val rectangles = comparisonService.getRectangles(1, 3)
        val result = comparisonService.isOverlapping(rectangles)

        assertEquals("No Intersections", result)
    }

    @Test
    fun `Check if a rectangle has intersections with another and it has`() {
        every { rectangleService.getOne(1) } returns constructRectangle1()
        every { rectangleService.getOne(2) } returns constructRectangle2()
        val rectangles = comparisonService.getRectangles(1, 2)
        val result = comparisonService.isOverlapping(rectangles)

        assertEquals("The intersection coordinates are: (50,200) (100,50)", result)
    }

    @Test
    fun `Check if a rectangle is adjacent with another and is not`() {
        every { rectangleService.getOne(1) } returns constructRectangle1()
        every { rectangleService.getOne(2) } returns constructRectangle2()
        val rectangles = comparisonService.getRectangles(1, 2)
        val result = comparisonService.isAdjacent(rectangles)

        assertEquals(AdjacentType.NO_ADJACENT, result)
    }

    @Test
    fun `Check if a rectangle is adjacent with another and is sub line`() {
        every { rectangleService.getOne(1) } returns constructRectangle1()
        every { rectangleService.getOne(4) } returns constructRectangle4()
        val rectangles = comparisonService.getRectangles(1, 4)
        val result = comparisonService.isAdjacent(rectangles)

        assertEquals(AdjacentType.SUB_LINE, result)
    }

    @Test
    fun `Check if a rectangle is adjacent with another and is partial`() {
        every { rectangleService.getOne(1) } returns constructRectangle1()
        every { rectangleService.getOne(5) } returns constructRectangle5()
        val rectangles = comparisonService.getRectangles(1, 5)
        val result = comparisonService.isAdjacent(rectangles)

        assertEquals(AdjacentType.PARTIAL, result)
    }

    @Test
    fun `Check if a rectangle is adjacent with another and is proper`() {
        every { rectangleService.getOne(1) } returns constructRectangle1()
        every { rectangleService.getOne(6) } returns constructRectangle6()
        val rectangles = comparisonService.getRectangles(1, 6)
        val result = comparisonService.isAdjacent(rectangles)

        assertEquals(AdjacentType.PROPER, result)
    }

    private fun constructRectangle1(): Rectangle{
        return Rectangle(1, 0,0,100, 200)
    }

    private fun constructRectangle2(): Rectangle{
        return Rectangle(2, 50, 50, 150, 250)
    }

    private fun constructRectangle3(): Rectangle{
        return Rectangle(3, 50, 50, 10, 20)
    }

    private fun constructRectangle4(): Rectangle{
        return Rectangle(4, 100, 200, 120, 130)
    }

    private fun constructRectangle5(): Rectangle{
        return Rectangle(4, 100, 200, 100, 200)
    }

    private fun constructRectangle6(): Rectangle{
        return Rectangle(4, 100, 0, 20, 200)
    }
}