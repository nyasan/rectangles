package com.nuvelance.rectangles.controller

import com.nuvelance.rectangles.entity.Rectangle
import com.nuvelance.rectangles.service.RectangleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rectangle")
class RectangleController(private val rectangleService: RectangleService) {

    @GetMapping("/all")
    fun getRectangles():ResponseEntity<List<Rectangle>>{
        val result = rectangleService.getAll()
        return ResponseEntity(result, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getRectangle(@PathVariable id: Long):ResponseEntity<Rectangle>{
        val result = rectangleService.getOne(id)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping
    fun createRectangle(sides12: Int, sides34: Int):ResponseEntity<Rectangle>{
        val result = rectangleService.createRectangle(sides12, sides34)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PutMapping
    fun updateRectangle(id:Long, sides12: Int, sides34: Int):ResponseEntity<Rectangle>{
        val result = rectangleService.updateRectangle(id, sides12, sides34)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @DeleteMapping
    fun updateRectangle(id:Long):ResponseEntity<String>{
        rectangleService.deleteRectangle(id)
        return ResponseEntity("deleted complete",HttpStatus.OK)
    }
}