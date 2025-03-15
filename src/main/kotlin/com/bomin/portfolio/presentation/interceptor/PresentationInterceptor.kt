package com.bomin.portfolio.presentation.interceptor

import com.bomin.portfolio.domain.entity.HttpInterface
import com.bomin.portfolio.domain.repository.HttpInterfaceRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class PresentationInterceptor(
    private val httpInterfaceRepository: HttpInterfaceRepository
) : HandlerInterceptor {

    // postHandle(), afterCompletion() 메서드는 각각 요청 처리가 완료된 후에 실행되는 메서드
    // postHandle() 컨트롤러가 예외를 던지면 동작하지 않지만, afterCompletion()은 예외가 발생해도 동작
    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        val httpInterface = HttpInterface(request)
        httpInterfaceRepository.save(httpInterface)

    }

}