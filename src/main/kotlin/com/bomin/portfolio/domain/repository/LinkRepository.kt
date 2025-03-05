package com.bomin.portfolio.domain.repository

import com.bomin.portfolio.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Long> {
}