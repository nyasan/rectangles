package com.nuvelance.rectangles.service

import com.nuvelance.rectangles.entity.Rectangle
import org.springframework.stereotype.Service
import kotlin.math.max
import kotlin.math.min

@Service
class ComparisonService(private val rectangleService: RectangleService) {

    fun isContained(rectangles: Pair<Rectangle, Rectangle>): Boolean {
        val (outerRectangle, innerRectangle) = rectangles

        return (innerRectangle.x > outerRectangle.x &&
                innerRectangle.y > outerRectangle.y &&
                innerRectangle.x + innerRectangle.width < outerRectangle.x + outerRectangle.width &&
                innerRectangle.y + innerRectangle.height < outerRectangle.y + outerRectangle.height
                )
    }

    fun isAdjacent(rectangles: Pair<Rectangle, Rectangle>): String {
        val (firstRectangle, secondRectangle) = rectangles
        val p1 = firstRectangle.x + firstRectangle.width
        val p2 = firstRectangle.y + firstRectangle.height
        if (p1 == secondRectangle.x && firstRectangle.y == secondRectangle.y && firstRectangle.height == secondRectangle.height) {
            return "Adjacent is Proper"
        }
        if (p1 == secondRectangle.x && IntRange(
                firstRectangle.y,
                p2
            ).contains(secondRectangle.y)
        ) {
            if (firstRectangle.height > secondRectangle.height) {
                return "Adjacent is Sub-line"
            }
            return "Adjacent is Partial"
        }
        return "No adjacent"
    }

    fun isOverlapping(rectangles: Pair<Rectangle, Rectangle>): String {
        val (firstRectangle, secondRectangle) = rectangles
        if (isContained(rectangles) || isAdjacent(rectangles) != "No adjacent" || firstRectangle.x > secondRectangle.x + secondRectangle.width ||
            secondRectangle.x > firstRectangle.x + firstRectangle.width ||
            secondRectangle.y > firstRectangle.y + firstRectangle.height ||
            firstRectangle.y > secondRectangle.y + secondRectangle.height) {
            return "No Intersections"
        }
        var x5 = max(firstRectangle.x, secondRectangle.x)
        var y5 = max(firstRectangle.y, secondRectangle.y)
        val x6 = min(firstRectangle.x+firstRectangle.width,secondRectangle.x+secondRectangle.width)
        var y6 = min(firstRectangle.y+firstRectangle.height, secondRectangle.y+secondRectangle.height)

        if(IntRange(firstRectangle.y, firstRectangle.y+firstRectangle.height).contains(secondRectangle.y)&&
            IntRange(firstRectangle.y, firstRectangle.y+firstRectangle.height).contains(secondRectangle.y+secondRectangle.height)){
            x5 = x6
        }
        if(IntRange(firstRectangle.x, firstRectangle.x+firstRectangle.width).contains(secondRectangle.x)&&
            IntRange(firstRectangle.x, firstRectangle.x+firstRectangle.width).contains(secondRectangle.x+secondRectangle.width)){
            y5 = y6
        }
        if(IntRange(secondRectangle.y, secondRectangle.y+secondRectangle.height).contains(firstRectangle.y)){
            y6 = y5
        }

        return "The intersection coordinates are: ($x5,$y6) ($x6,$y5)"
    }

    fun getRectangles(
        firstRectangle: Long,
        secondRectangle: Long
    ): Pair<Rectangle, Rectangle> {
        val outerRectangle = rectangleService.getOne(firstRectangle)
        val innerRectangle = rectangleService.getOne(secondRectangle)
        return Pair(outerRectangle, innerRectangle)
    }
}