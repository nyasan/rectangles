package com.nuvelance.rectangles.service

import com.nuvelance.rectangles.entity.Rectangle
import com.nuvelance.rectangles.repository.RectangleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RectangleService(private val rectangleRepository: RectangleRepository) {

    fun getAll(): List<Rectangle> {
        return rectangleRepository.findAll()
    }

    fun getOne(id: Long): Rectangle {
        return rectangleRepository.findById(id).get()
    }

    fun createRectangle(x: Int, y: Int, width: Int, height: Int): Rectangle {
        return rectangleRepository.save(Rectangle(null, x, y, width, height))
    }

    fun updateRectangle(
        id: Long,
        x: Optional<Int>,
        y: Optional<Int>,
        width: Optional<Int>,
        height: Optional<Int>
    ): Rectangle {
        val updateRectangle = getOne(id)
        if (x.isPresent) updateRectangle.x = x.get()
        if (y.isPresent) updateRectangle.y = y.get()
        if (width.isPresent) updateRectangle.width = width.get()
        if (height.isPresent) updateRectangle.height = height.get()

        return rectangleRepository.save(updateRectangle)
    }

    fun deleteRectangle(id: Long) {
        return rectangleRepository.deleteById(id)
    }
}