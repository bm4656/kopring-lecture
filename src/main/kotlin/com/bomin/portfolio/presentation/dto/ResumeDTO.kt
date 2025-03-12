package com.bomin.portfolio.presentation.dto

import com.bomin.portfolio.domain.entity.Achievement
import com.bomin.portfolio.domain.entity.Experience
import com.bomin.portfolio.domain.entity.Skill
import java.time.format.DateTimeFormatter

class ResumeDTO(
    experience: List<Experience>,
    achievement: List<Achievement>,
    skills: List<Skill>
) {
    var experience: List<ExperienceDTO> = experience.map {
        ExperienceDTO(
            title = it.title,
            description = it.description,
            startYearMonth = "${it.startYear}.${it.startMonth}",
            endYearMonth = it.getEndYearMonth(),
            details = it.details.filter { it.isActive }.map { it.content }
        )
    }

    var achievement: List<AchievementDTO> = achievement.map {
        AchievementDTO(
            title = it.title,
            description = it.description,
            host = it.host,
            achievedDate = it.achievedDate
                ?.format(DateTimeFormatter.ISO_LOCAL_DATE)
                ?.replace("-", ".").toString()
        )
    }

    var skills: List<SkillDTO> = skills.map { SkillDTO(it) }
}