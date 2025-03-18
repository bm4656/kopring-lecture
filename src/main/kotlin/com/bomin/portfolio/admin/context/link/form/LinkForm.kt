package com.bomin.portfolio.admin.context.link.form

import com.bomin.portfolio.domain.entity.Link
import jakarta.validation.constraints.NotBlank

data class LinkForm(
    @field:NotBlank(message = "필수값입니다.")
    val name: String,

    @field:NotBlank(message = "필수값입니다.")
    val content: String,

    val isActive: Boolean,
) {
    fun toEntity(): Link {
        return Link(
            name = name,
            content = content,
            isActive = isActive,
        )
    }

    fun toEntity(id: Long): Link {
        val link = this.toEntity()
        link.id = id

        return link
    }
}