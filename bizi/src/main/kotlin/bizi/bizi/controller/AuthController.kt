package bizi.bizi.controller

import bizi.bizi.DTO.SignInDTO
import bizi.bizi.DTO.UserDTO
import bizi.bizi.security.UserDetailsImpl
import bizi.bizi.service.JwtService
import bizi.bizi.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(private val userService: UserService, private val jwtService: JwtService) {
    @PostMapping("/register")
    fun register(@RequestBody user: UserDTO): String {
        val registeredUser = userService.registerUser(user)
        return jwtService.generateToken(UserDetailsImpl(registeredUser))
    }

    @PostMapping("/login")
    fun login(@RequestBody credentials: SignInDTO): String {
        val logedUser = userService.signIn(credentials)
        return jwtService.generateToken(UserDetailsImpl(logedUser))
    }
}
