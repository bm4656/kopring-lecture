package com.bomin.portfolio.admin.context.project.service

import com.bomin.portfolio.admin.context.project.form.ProjectForm
import com.bomin.portfolio.admin.data.TableDTO
import com.bomin.portfolio.admin.exception.AdminBadRequestException
import com.bomin.portfolio.domain.entity.Project
import com.bomin.portfolio.domain.repository.ProjectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminProjectService(
    private val projectRepository: ProjectRepository
) {
    fun getProjectDetailTable(): TableDTO {
        val classInfo = Project::class
        val entities = projectRepository.findAll()

        return TableDTO.from(classInfo, entities, "details", "skills")
    }

    fun getProjectDetailTable(id: Long?): TableDTO {
        val classInfo = Project::class
        val entities = if (id != null) projectRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수가 없습니다.") }
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: ProjectForm) {
        val projectDetails = form.details
            ?.map { detail -> detail.toEntity() }
            ?.toMutableList()

        val project = form.toEntity()
        project.addDetails(projectDetails)

        projectRepository.save(project)
    }

    @Transactional
    fun update(id: Long, form: ProjectForm) {
        val project = projectRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수가 없습니다.") }

        project.update(
            name = form.name,
            description = form.description,
            startYear = form.startYear,
            startMonth = form.startMonth,
            endYear = form.endYear,
            endMonth = form.endMonth,
            isActive = form.isActive,
        )

        val detailMap = project.details.map { it.id to it }.toMap()
        form.details?.forEach {
            val entity = detailMap.get(it.id)
            if (entity != null) {
                entity.update(
                    content = it.content,
                    url = it.url,
                    isActive = it.isActive
                )
            } else {
                project.details.add(it.toEntity())
            }
        }
        // 연관관계가 없는 테이블의 update 메소드는 save() 메소드를 호출했지만, 여기서는 save()를 명시하여 호출하지 않음
        // -> JPA 더티체킹에 의해 트랜잭션이 종료될 때 업데이트가 자동으로 반영되는 것을 생각
    }
}