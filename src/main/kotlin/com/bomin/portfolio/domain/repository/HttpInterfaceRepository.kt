package com.bomin.portfolio.domain.repository

import com.bomin.portfolio.domain.entity.HttpInterface
import org.springframework.data.jpa.repository.JpaRepository

interface HttpInterfaceRepository : JpaRepository<HttpInterface, Long> {
}