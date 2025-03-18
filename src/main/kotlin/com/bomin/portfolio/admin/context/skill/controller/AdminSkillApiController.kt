package com.bomin.portfolio.admin.context.skill.controller

import com.bomin.portfolio.admin.context.skill.form.SkillForm
import com.bomin.portfolio.admin.context.skill.service.AdminSkillService
import com.bomin.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/skill")
class AdminSkillApiController(
    private val adminSkillService: AdminSkillService
) {
    @PostMapping
    fun postSkill(@RequestBody @Validated skillForm: SkillForm): ResponseEntity<Any> {
        adminSkillService.save(skillForm)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putSkill(@PathVariable id: Long, @RequestBody skillForm: SkillForm): ResponseEntity<Any> {
        adminSkillService.update(id, skillForm)

        return ApiResponse.successUpdate()
    }
}