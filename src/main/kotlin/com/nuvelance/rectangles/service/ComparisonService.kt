package com.nuvelance.rectangles.service

import org.springframework.stereotype.Service

@Service
class ComparisonService(private val rectangleService: RectangleService) {

    fun isContained(outerRectangleId: Long, innerRectangleId: Long): Boolean {
        val outerRectangle = rectangleService.getOne(outerRectangleId)
        val innerRectangle = rectangleService.getOne(innerRectangleId)

        return (innerRectangle.x > outerRectangle.x &&
                innerRectangle.y > outerRectangle.y &&
                innerRectangle.x + innerRectangle.width < outerRectangle.x + outerRectangle.width &&
                innerRectangle.y + innerRectangle.height < outerRectangle.y + outerRectangle.height
                )
    }
}