package esperer.hexakonal.domain.user.application.usecase

import esperer.hexakonal.domain.user.adapter.presentation.data.request.SignUpRequest
import esperer.hexakonal.domain.user.adapter.presentation.data.request.toDomain
import esperer.hexakonal.domain.user.application.port.CommandAccountPort
import esperer.hexakonal.domain.user.application.port.PasswordEncodePort
import esperer.hexakonal.domain.user.application.port.QueryUserPort
import esperer.hexakonal.domain.user.exception.DuplicateEmailException
import esperer.hexakonal.global.annotation.usecase.TransactionalUseCase
import java.util.*

@TransactionalUseCase
class SignUpUseCase(
    private val commandAccountPort: CommandAccountPort,
    private val queryUserPort: QueryUserPort,
    private val passwordEncodePort: PasswordEncodePort
) {

    fun execute(request: SignUpRequest): UUID {
        if(queryUserPort.existsUserByEmail(request.email)) {
            throw DuplicateEmailException()
        }
        return commandAccountPort.saveUser(request.toDomain(), passwordEncodePort.passwordEncode(request.password)).id
    }
}