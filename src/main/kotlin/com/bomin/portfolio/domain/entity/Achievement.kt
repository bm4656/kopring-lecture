package com.bomin.portfolio.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Achievement(
    title: String,
    description: String,
    host: String,
    achievedDate: LocalDate?,
    isActive: Boolean
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    var id: Long? = null

    var title: String = title

    var description: String = description

    var host: String = host

    var achievedDate: LocalDate? = achievedDate


    var isActive: Boolean = isActive
}