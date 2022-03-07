package com.nuvelance.rectangles.service

import com.nuvelance.rectangles.entity.Rectangle
import com.nuvelance.rectangles.repository.RectangleRepository
import org.springframework.stereotype.Service

@Service
class RectangleService(private val rectangleRepository: RectangleRepository) {

    fun getAll(): List<Rectangle>{
        return rectangleRepository.findAll()
    }

    fun getOne(id: Long): Rectangle{
        return rectangleRepository.findById(id).get()
    }

    fun createRectangle(side1: Int, side2: Int): Rectangle{
        return rectangleRepository.save(Rectangle(null, side1, side2, side1, side2))
    }

    fun updateRectangle(id: Long, side1: Int, side2: Int): Rectangle{
        val updateRectangle = getOne(id)
        updateRectangle.side1 = side1
        updateRectangle.side2 = side2
        updateRectangle.side3 = side1
        updateRectangle.side4 = side2
        return rectangleRepository.save(updateRectangle)
    }

    fun deleteRectangle(id: Long){
        return rectangleRepository.deleteById(id)
    }
}