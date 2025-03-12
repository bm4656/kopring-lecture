package com.bomin.portfolio.presentation.dto

import com.bomin.portfolio.domain.entity.ProjectDetail

data class ProjectDetailDTO(
    val content: String,
    val url: String?
) {
    constructor(projecDetail: ProjectDetail) : this(
        content = projecDetail.content,
        url = projecDetail.url
    )
}