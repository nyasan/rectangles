package com.nuvelance.rectangles.service

import com.nuvelance.rectangles.entity.Rectangle
import com.nuvelance.rectangles.util.AdjacentType
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

    fun isAdjacent(rectangles: Pair<Rectangle, Rectangle>): AdjacentType {
        val (firstRectangle, secondRectangle) = rectangles
        val x2 = firstRectangle.width
        val y1 = firstRectangle.y
        val y2 = firstRectangle.height

        val x3 = secondRectangle.x
        val y3 = secondRectangle.y
        val y4 = secondRectangle.height

        val p1 = firstRectangle.x + x2
        val p2 = y1 + y2
        if (p1 == x3 && y1 == y3 && y2 == y4) {
            return AdjacentType.PROPER
        }
        if (p1 == x3 && IntRange(
                y1,
                p2
            ).contains(y3)
        ) {
            if (y2 > y4) {
                return AdjacentType.SUB_LINE
            }
            return AdjacentType.PARTIAL
        }
        return AdjacentType.NO_ADJACENT
    }

    fun isNotOverlapping(rectangles: Pair<Rectangle, Rectangle>): String {
        val (firstRectangle, secondRectangle) = rectangles
        if (isNotOverlapping(rectangles, firstRectangle, secondRectangle)) {
            return "No Intersections"
        }
        val x1 = firstRectangle.x
        val y1 = firstRectangle.y
        val x2 = firstRectangle.width
        val y2 = firstRectangle.height

        val x3 = secondRectangle.x
        val y3 = secondRectangle.y
        val x4 = secondRectangle.width
        val y4 = secondRectangle.height

        var x5 = max(x1, x3)
        var y5 = max(y1, y3)
        val x6 = min(x1 + x2, x3 + x4)
        var y6 = min(y1 + y2, y3 + y4)

        if (IntRange(y1, y1 + y2).contains(y3) &&
            IntRange(y1, y1 + y2).contains(y3 + y4)
        ) {
            x5 = x6
        }
        if (IntRange(x1, x1 + x2).contains(x3) &&
            IntRange(x1, x1 + x2).contains(x3 + x4)
        ) {
            y5 = y6
        }
        if (IntRange(y3, y3 + y4).contains(y1)) {
            y6 = y5
        }

        return "The intersection coordinates are: ($x5,$y6) ($x6,$y5)"
    }

    private fun isNotOverlapping(
        rectangles: Pair<Rectangle, Rectangle>,
        firstRectangle: Rectangle,
        secondRectangle: Rectangle
    ) = isContained(rectangles) || isAdjacent(rectangles) != AdjacentType.NO_ADJACENT ||
            firstRectangle.x > secondRectangle.x + secondRectangle.width ||
            secondRectangle.x > firstRectangle.x + firstRectangle.width ||
            secondRectangle.y > firstRectangle.y + firstRectangle.height ||
            firstRectangle.y > secondRectangle.y + secondRectangle.height

    fun getRectangles(
        firstRectangleId: Long,
        secondRectangleId: Long
    ): Pair<Rectangle, Rectangle> {
        val firstRectangle = rectangleService.getOne(firstRectangleId)
        val secondRectangle = rectangleService.getOne(secondRectangleId)
        return Pair(firstRectangle, secondRectangle)
    }
}