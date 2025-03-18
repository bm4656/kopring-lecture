package com.bomin.portfolio.admin.context.project.controller

import com.bomin.portfolio.admin.context.project.form.ProjectForm
import com.bomin.portfolio.admin.context.project.service.AdminProjectService
import com.bomin.portfolio.admin.data.ApiResponse
import com.bomin.portfolio.admin.data.TableDTO
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/projects")
class AdminProjectApiController(
    private val adminProjectService: AdminProjectService
) {
    @PostMapping
    fun postProject(@RequestBody @Validated projectForm: ProjectForm): ResponseEntity<Any> {
        adminProjectService.save(projectForm)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putProject(@PathVariable id: Long, @RequestBody projectForm: ProjectForm): ResponseEntity<Any> {
        adminProjectService.update(id, projectForm)

        return ApiResponse.successUpdate()
    }

    @GetMapping("/{id}/details")
    fun getProjectDetails(@PathVariable id: Long): TableDTO {
        return adminProjectService.getProjectDetailTable(id)
    }
}