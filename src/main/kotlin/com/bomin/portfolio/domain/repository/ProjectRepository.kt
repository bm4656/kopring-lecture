package com.bomin.portfolio.domain.repository

import com.bomin.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long> {
}