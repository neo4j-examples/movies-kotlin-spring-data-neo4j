package movies.spring.data.neo4j.api.service.authorization;

import movies.spring.data.neo4j.api.service.authorization.dto.AuthorizationDTO
import movies.spring.data.neo4j.api.service.authorization.dto.CredentialsDTO
import movies.spring.data.neo4j.exception.UnauthorizedException
import movies.spring.data.neo4j.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AuthorizationServicePasswordImpl @Autowired constructor(
        userRepo: UserRepository) : AuthorizationService
{
    private val userRepository: UserRepository = userRepo

    @Transactional
    override fun authorize(credentials: CredentialsDTO): AuthorizationDTO
    {
        val user = userRepository.findByEmail(credentials.email)
        if (user == null || user.password != credentials.password)
        {
            throw UnauthorizedException("The provided credentials were not valid.")
        }
        return AuthorizationDTO.fromUser(user)
    }
}
