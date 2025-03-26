package com.bomin.portfolio.domain

import com.bomin.portfolio.domain.constant.SkillType
import com.bomin.portfolio.domain.entity.*
import com.bomin.portfolio.domain.repository.*
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component()
@Profile(value = ["default"])
class DataInitializer(
    private val achievementRepository: AchievementRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val skillRepository: SkillRepository,
    private val projectRepository: ProjectRepository,
    private val experienceRepository: ExperienceRepository,
) {
    @PostConstruct
    fun initializeData() {
        println("스프링이 실행되었습니다. 테스트 데이터를 초기화합니다.")

        // achievement 초기화
        val achievements = mutableListOf<Achievement>(
            Achievement(
                title = "[수상] UNITHON 11TH 해커톤 대상",
                description = "AI 기반 톡방 대화 분석 서비스 <2024 대화 평화상>",
                host = "UNITHON",
                achievedDate = LocalDate.of(2024, 4, 6),
                isActive = true
            ),
            Achievement(
                title = "[수상] 2023 DMU 스마트 프로젝트 경진대회 우수상",
                description = "컴퓨터소프트웨어공학과 졸업작품 <What Song>",
                host = "동양미래대학교",
                achievedDate = LocalDate.of(2023, 12, 17),
                isActive = true
            ),
            Achievement(
                title = "[자격증]AWS Certified Cloud Practitioner",
                description = "컴퓨터소프트웨어공학과 졸업작품 <What Song>",
                host = "동양미래대학교",
                achievedDate = LocalDate.of(2022, 7, 24),
                isActive = true
            ),
        )

        achievementRepository.saveAll(achievements)

        // introduction 초기화
        val introductions = mutableListOf<Introduction>(
            Introduction(content = "'유저의 입장에서 사소한 디테일까지' 세심하게 실현하려 노력하는 김보민입니다.", isActive = true),
            Introduction(
                content = "코드의 품질과 프로젝트 고도화를 위한 리팩터링을 수행하며 최적화된 개발을 하고자 합니다.",
                isActive = true
            ),
            Introduction(
                content = "사용자가 이해가 가는 서비스를 만드는 것을 목표로 하고 있습니다.",
                isActive = true
            )
        )

        introductionRepository.saveAll(introductions)

        // link 초기화
        val links = mutableListOf<Link>(
            Link(name = "Github", content = "https://github.com/bm4656", isActive = true),
        )

        linkRepository.saveAll(links)

        // experience 초기화
        val experience1 = Experience(
            title = "동양미래대학교",
            description = "컴퓨터소프트웨어공학과(학사)",
            startYear = 2023,
            startMonth = 3,
            endYear = 2024,
            endMonth = 2,
            isActive = true,
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(content = "GPA 4.35/4.5", isActive = true),
            )
        )

        val experience2 = Experience(
            title = "동양미래대학교",
            description = "컴퓨터소프트웨어공학과(3년)",
            startYear = 2020,
            startMonth = 3,
            endYear = 2023,
            endMonth = 2,
            isActive = true,
        )
        experience2.addDetails(
            mutableListOf(
                ExperienceDetail(content = "GPA 4.18/4.5", isActive = true),
            )
        )

        experienceRepository.saveAll(mutableListOf(experience1, experience2))

        // skill 초기화 - 만든 엔티티를 변수에 할당 -> 아래 project init에서 사용
        val node = Skill(name = "NodeJS", type = SkillType.LANGUAGE.name, isActive = true)
        val nest = Skill(name = "NestJS", type = SkillType.FRAMEWORK.name, isActive = true)
        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val spring = Skill(name = "Spring", type = SkillType.FRAMEWORK.name, isActive = true)
        val mysql = Skill(name = "MySQL", type = SkillType.DATABASE.name, isActive = true)


        skillRepository.saveAll(
            mutableListOf(
                node, nest,
                java, spring, mysql,
            )
        )

        // project / project_detail / project_skill 초기화
        val project1 = Project(
            name = "운동 동호회 모집 및 커뮤니티 서비스",
            description = "운동 특화 커뮤니티 서비스로, 운동 동호회 모집 및 운동 정보 공유를 위한 서비스입니다.",
            startYear = 2025,
            startMonth = 3,
            endYear = null,
            endMonth = null,
            isActive = true
        )
        project1.addDetails(
            mutableListOf(
                ProjectDetail(content = "아직 진행중이라 내용 추가가 필요합니다.", url = null, isActive = true),
            )
        )
        // 위의 addDetails 처럼 project안에 메소드로 묶어도됨, 방법을 알려주기 위해 두가지 방법 혼용
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = java),
                ProjectSkill(project = project1, skill = spring),
                ProjectSkill(project = project1, skill = mysql),
            )
        )


        projectRepository.saveAll(mutableListOf(project1))
    }
}