package com.nuvelance.rectangles.controller

import com.nuvelance.rectangles.service.ComparisonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comparison")
class ComparisonController(private val comparisonService: ComparisonService) {

    @GetMapping
    fun isContained(
        @RequestParam(required = true) outerRectangleId: Long,
        @RequestParam(required = true) innerRectangleId: Long
    ): ResponseEntity<String> {
        val result = comparisonService.isContained(outerRectangleId, innerRectangleId)
        return if (result) ResponseEntity("Is contained", HttpStatus.OK) else ResponseEntity(
            "Is not contained",
            HttpStatus.OK
        )
    }
}