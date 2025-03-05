package com.bomin.portfolio.domain.repository

import com.bomin.portfolio.domain.entity.HttpInterface
import com.bomin.portfolio.domain.entity.Introduction
import org.springframework.data.jpa.repository.JpaRepository

interface IntroductionRepository : JpaRepository<Introduction, Long> {
}