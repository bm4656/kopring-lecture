package com.bomin.portfolio.admin.context.project.controller

import com.bomin.portfolio.admin.context.project.form.ProjectSkillForm
import com.bomin.portfolio.admin.context.project.service.AdminProjectSkillService
import com.bomin.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/projects/skills")
class AdminProjectSkillApiController(
    private val adminProjectSkillService: AdminProjectSkillService
) {
    @PostMapping
    fun postProject(@RequestBody form: ProjectSkillForm): ResponseEntity<Any> {
        adminProjectSkillService.save(form)

        return ApiResponse.successCreate()
    }

    @DeleteMapping("/{id}")
    fun deleteProjectSkill(@PathVariable("id") id: Long): ResponseEntity<Any> {
        adminProjectSkillService.deleteProjectSkill(id)

        return ApiResponse.successDelete()
    }
}