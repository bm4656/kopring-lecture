package com.bomin.portfolio.domain.repository

import com.bomin.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<Skill, Long> {
}