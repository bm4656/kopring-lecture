package com.bomin.portfolio.admin.context.achievement.service

import com.bomin.portfolio.admin.data.TableDTO
import com.bomin.portfolio.domain.entity.Achievement
import com.bomin.portfolio.domain.repository.AchievementRepository
import org.springframework.stereotype.Service

@Service
class AdminAchievementService(
    private val achievementRepository: AchievementRepository
) {
    fun getAchievementTable(): TableDTO {
        val classInfo = Achievement::class
        val entities = achievementRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }
}