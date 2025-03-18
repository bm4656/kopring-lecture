package com.bomin.portfolio.admin.context.experience.service

import com.bomin.portfolio.admin.context.experience.form.ExperienceForm
import com.bomin.portfolio.admin.data.TableDTO
import com.bomin.portfolio.admin.exception.AdminBadRequestException
import com.bomin.portfolio.domain.entity.Experience
import com.bomin.portfolio.domain.repository.ExperienceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminExperienceService(
    private val experienceRepository: ExperienceRepository
) {
    fun getExperienceDetailTable(): TableDTO {
        val classInfo = Experience::class
        val entities = experienceRepository.findAll()

        return TableDTO.from(classInfo, entities, "details")
    }

    fun getExperienceDetailTable(id: Long?): TableDTO {
        val classInfo = Experience::class
        val entities = if (id != null) experienceRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수가 없습니다.") }
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: ExperienceForm) {
        val experienceDetails = form.details
            ?.map { detail -> detail.toEntity() }
            ?.toMutableList()

        val experience = form.toEntity()
        experience.addDetails(experienceDetails) // cascade 속성이 ALL이므로 experienceDetails도 저장됨

        experienceRepository.save(experience)
    }

    @Transactional
    fun update(id: Long, form: ExperienceForm) {
        val experience = experienceRepository.findById(id)
            .orElseThrow { throw AdminBadRequestException("ID ${id}에 해당하는 데이터를 찾을 수가 없습니다.") }

        experience.update(
            title = form.title,
            description = form.description,
            startYear = form.startYear,
            startMonth = form.startMonth,
            endYear = form.endYear,
            endMonth = form.endMonth,
            isActive = form.isActive,
        )

        val detailMap = experience.details.map { it.id to it }.toMap()
        form.details?.forEach {
            val entity = detailMap.get(it.id)
            if (entity != null) {
                entity.update(
                    content = it.content,
                    isActive = it.isActive
                )
            } else {
                experience.details.add(it.toEntity())
            }
        }
        // 연관관계가 없는 테이블의 update 메소드는 save() 메소드를 호출했지만, 여기서는 save()를 명시하여 호출하지 않음
        // -> JPA 더티체킹에 의해 트랜잭션이 종료될 때 업데이트가 자동으로 반영되는 것을 생각
    }
}