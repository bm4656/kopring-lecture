package com.bomin.portfolio.admin.context.project.controller

import com.bomin.portfolio.admin.context.project.service.AdminProjectService
import com.bomin.portfolio.admin.data.FormElementDTO
import com.bomin.portfolio.admin.data.SelectFormElementDTO
import com.bomin.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/project")
class AdminProjectController(
    private val adminProjectService: AdminProjectService
) {
    @GetMapping
    fun project(model: Model): String {

        val elements = listOf<FormElementDTO>(
            TextFormElementDTO("name", 4),
            TextFormElementDTO("description", 8),
            SelectFormElementDTO("startYear", 3, (2010..2030).toList()),
            SelectFormElementDTO("startMonth", 2, (1..12).toList()),
            SelectFormElementDTO("endYear", 3, (2010..2030).toList()),
            SelectFormElementDTO("endMonth", 2, (1..12).toList()),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("elements", elements)

        val detailElements = listOf<FormElementDTO>(
            TextFormElementDTO("content", 4),
            TextFormElementDTO("url", 6),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("detailElements", detailElements)

        val table = adminProjectService.getProjectDetailTable()
        model.addAttribute("table", table)

        val detailTable = adminProjectService.getProjectDetailTable(null)
        model.addAttribute("detailTable", detailTable)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Project"),
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", true)
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}