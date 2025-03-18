package com.bomin.portfolio.admin.context.experience.controller

import com.bomin.portfolio.admin.context.experience.form.ExperienceForm
import com.bomin.portfolio.admin.context.experience.service.AdminExperienceService
import com.bomin.portfolio.admin.data.ApiResponse
import com.bomin.portfolio.admin.data.TableDTO
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/experiences")
class AdminExperienceApiController(
    private val adminExperienceService: AdminExperienceService
) {
    @PostMapping
    fun postExperience(@RequestBody @Validated experienceForm: ExperienceForm): ResponseEntity<Any> {
        adminExperienceService.save(experienceForm)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putExperience(@PathVariable id: Long, @RequestBody experienceForm: ExperienceForm): ResponseEntity<Any> {
        adminExperienceService.update(id, experienceForm)

        return ApiResponse.successUpdate()
    }

    @GetMapping("/{id}/details")
    fun getExperienceDetails(@PathVariable id: Long): TableDTO {
        return adminExperienceService.getExperienceDetailTable(id)
    }
}