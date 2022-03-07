package com.nuvelance.rectangles.controller

import com.nuvelance.rectangles.entity.Rectangle
import com.nuvelance.rectangles.service.RectangleService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/rectangle")
class RectangleController(private val rectangleService: RectangleService) {

    @GetMapping("/all")
    @ApiOperation("Get all rectangles")
    fun getRectangles(): ResponseEntity<List<Rectangle>> {
        val result = rectangleService.getAll()
        return ResponseEntity(result, HttpStatus.OK)
    }

    @GetMapping("/id/{id}")
    @ApiOperation("Get one rectangle by id")
    fun getRectangle(@PathVariable id: Long): ResponseEntity<Rectangle> {
        val result = rectangleService.getOne(id)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping
    @ApiOperation("Create a rectangle")
    fun createRectangle(
        @RequestParam(required = true) x: Int,
        @RequestParam(required = true) y: Int,
        @RequestParam(required = true) width: Int,
        @RequestParam(required = true) height: Int
    ): ResponseEntity<Rectangle> {
        val result = rectangleService.createRectangle(x, y, width, height)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PutMapping
    @ApiOperation("Update a rectangle")
    fun updateRectangle(
        @RequestParam(required = true) id: Long,
        @RequestParam(required = false) x: Optional<Int>,
        @RequestParam(required = false) y: Optional<Int>,
        @RequestParam(required = false) width: Optional<Int>,
        @RequestParam(required = false) height: Optional<Int>
    ): ResponseEntity<Rectangle> {
        val result = rectangleService.updateRectangle(id, x, y, width, height)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @DeleteMapping
    @ApiOperation("Delete a rectangle by id")
    fun deleteRectangle(@RequestParam(required = true) id: Long): ResponseEntity<String> {
        rectangleService.deleteRectangle(id)
        return ResponseEntity("deleted complete", HttpStatus.OK)
    }
}