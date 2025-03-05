package com.bomin.portfolio.domain.repository

import com.bomin.portfolio.domain.entity.Achievement
import org.springframework.data.jpa.repository.JpaRepository

interface AchievementRepository : JpaRepository<Achievement, Long> {
}