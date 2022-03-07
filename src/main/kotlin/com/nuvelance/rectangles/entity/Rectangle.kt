package com.nuvelance.rectangles.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Rectangle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var side1: Int,
    var side2: Int,
    var side3: Int,
    var side4: Int
)