package esperer.hexakonal.domain.user

import java.util.UUID

data class User(
    val id: UUID,
    val email: String,
    val encodedPassword: String,
    val name: String
)
