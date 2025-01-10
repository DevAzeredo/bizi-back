package bizi.bizi.security

import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.FilterChain
import bizi.bizi.service.JwtService
import bizi.bizi.service.UserService

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userService: UserService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authHeader = request.getHeader("Authorization")
            if (authHeader?.startsWith("Bearer ") == true) {
                val jwt = authHeader.substring(7)
                if (jwtService.validateToken(jwt)) {
                    val login = jwtService.getLoginFromToken(jwt)
                    val user = userService.findByLogin(login)
                    val authentication = UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        listOf(SimpleGrantedAuthority("ROLE_USER"))
                    )
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        } catch (e: Exception) {
            logger.error("Cannot set user authentication", e)
        }

        filterChain.doFilter(request, response)
    }
}
