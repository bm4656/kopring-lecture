package com.bomin.portfolio.admin.context.introduction.controller

import com.bomin.portfolio.admin.context.introduction.form.IntroductionForm
import com.bomin.portfolio.admin.context.introduction.service.AdminIntroductionService
import com.bomin.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/introductions")
class AdminIntroductionApiController(
    private val adminIntroductionService: AdminIntroductionService
) {
    @PostMapping
    fun postIntroduction(@RequestBody @Validated introductionForm: IntroductionForm): ResponseEntity<Any> {
        adminIntroductionService.save(introductionForm)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putIntroduction(@PathVariable id: Long, @RequestBody introductionForm: IntroductionForm): ResponseEntity<Any> {
        adminIntroductionService.update(id, introductionForm)

        return ApiResponse.successUpdate()
    }
}