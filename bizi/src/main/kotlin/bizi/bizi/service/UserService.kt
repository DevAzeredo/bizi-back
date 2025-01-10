package bizi.bizi.service

import bizi.bizi.DTO.SignInDTO
import bizi.bizi.DTO.UserDTO
import bizi.bizi.model.User
import bizi.bizi.repository.UserRepository
import bizi.bizi.security.UserDetailsImpl
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.context.annotation.Bean

@Service
class UserService(
        private val userRepository: UserRepository,
) {
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
    
    fun registerUser(newUser: UserDTO): User {
        userRepository.findByLogin(newUser.login)?.let {
            throw RuntimeException("User already exists")
        }
        val user = User(login = newUser.login, password = passwordEncoder().encode(newUser.password))
        return userRepository.save(user)
    }

    fun signIn(credentials: SignInDTO): User {
        val user =
                userRepository.findByLogin(credentials.login)
                        ?: throw RuntimeException("User not found")

        if (!passwordEncoder().matches(credentials.password, user.password)) {
            throw RuntimeException("Invalid password")
        }

        return user
    }

    fun findByLogin(login: String): User? {
        return userRepository.findByLogin(login)
    }
}
