package bizi.bizi.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import bizi.bizi.model.User
@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByLogin(login: String): User?
}