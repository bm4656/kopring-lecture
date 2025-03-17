package com.bomin.portfolio.admin.context.introduction.controller

import com.bomin.portfolio.admin.context.introduction.service.AdminIntroductionService
import com.bomin.portfolio.admin.data.FormElementDTO
import com.bomin.portfolio.admin.data.SelectFormElementDTO
import com.bomin.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/introduction")
class AdminIntroductionViewController(
    val adminIntroductionService: AdminIntroductionService
) {
    @GetMapping
    fun introduction(model: Model): String {
        val elements = listOf<FormElementDTO>(
            TextFormElementDTO("content", 20),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("elements", elements)

        val table = adminIntroductionService.getIntroductionTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Index"),
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", false)
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}