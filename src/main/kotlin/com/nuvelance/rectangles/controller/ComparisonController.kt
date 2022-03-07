package com.nuvelance.rectangles.controller

import com.nuvelance.rectangles.service.ComparisonService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comparison")
class ComparisonController(private val comparisonService: ComparisonService) {

    @GetMapping("/isContained")
    @ApiOperation("Return if a Rectangle is contained inside another")
    fun isContained(
        @RequestParam(required = true) outerRectangleId: Long,
        @RequestParam(required = true) innerRectangleId: Long
    ): ResponseEntity<String> {
        val rectangles = comparisonService.getRectangles(outerRectangleId, innerRectangleId)
        val result = comparisonService.isContained(rectangles)
        return if (result) ResponseEntity("Is contained", HttpStatus.OK) else ResponseEntity(
            "Is not contained",
            HttpStatus.OK
        )
    }

    @GetMapping("/isAdjacent")
    @ApiOperation("Return if a Rectangle is Proper, Sub-line or partial adjacent to another in one side")
    fun isAdjacent(
        @RequestParam(required = true) firstRectangle: Long,
        @RequestParam(required = true) secondRectangle: Long
    ): ResponseEntity<String> {
        val rectangles = comparisonService.getRectangles(firstRectangle, secondRectangle)
        val result = comparisonService.isAdjacent(rectangles)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @GetMapping("/isOverlapping")
    @ApiOperation("Return if a rectangle is overlapping another and coordinates where is overlapping")
    fun isOverlapping(
        @RequestParam(required = true) firstRectangle: Long,
        @RequestParam(required = true) secondRectangle: Long
    ): ResponseEntity<String> {
        val rectangles = comparisonService.getRectangles(firstRectangle, secondRectangle)
        val result = comparisonService.isOverlapping(rectangles)
        return ResponseEntity(result, HttpStatus.OK)
    }
}