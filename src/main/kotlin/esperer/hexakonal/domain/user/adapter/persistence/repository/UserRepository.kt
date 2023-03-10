package esperer.hexakonal.domain.user.adapter.persistence.repository

import esperer.hexakonal.domain.user.User
import esperer.hexakonal.domain.user.adapter.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): UserEntity?
}