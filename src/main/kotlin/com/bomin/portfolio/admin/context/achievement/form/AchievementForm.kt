package com.bomin.portfolio.admin.context.achievement.form

import com.bomin.portfolio.domain.entity.Achievement
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

// 일종의 DTO 클레스인데, 기능에 집중을 해서 form이라는 네이밍을 사용
data class AchievementForm(
    @field:NotBlank(message = "필수값입니다.")
    val title: String,

    @field:NotBlank(message = "필수값입니다.")
    val description: String,

    @field:NotBlank(message = "필수값입니다.")
    val host: String,

    @field:NotBlank(message = "필수값입니다.")
    val achievedDate: String,

    val isActive: Boolean,
) {
    fun toEntity(): Achievement {
        return Achievement(
            title = title,
            description = description,
            host = host,
            achievedDate = LocalDate.parse(achievedDate),
            isActive = isActive,
        )
    }

    fun toEntity(id: Long): Achievement {
        val achievement = this.toEntity()
        achievement.id = id

        return achievement
    }
}