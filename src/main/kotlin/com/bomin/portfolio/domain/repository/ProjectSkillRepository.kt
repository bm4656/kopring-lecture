package com.bomin.portfolio.domain.repository

import com.bomin.portfolio.domain.entity.ProjectSkill
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectSkillRepository : JpaRepository<ProjectSkill, Long> {
}