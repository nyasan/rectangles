package com.nuvelance.rectangles.repository

import com.nuvelance.rectangles.entity.Rectangle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RectangleRepository :JpaRepository<Rectangle, Long> {
}