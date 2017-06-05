package movies.spring.data.neo4j.api.service.authorization;

import movies.spring.data.neo4j.api.service.authorization.dto.AuthorizationDTO
import movies.spring.data.neo4j.api.service.authorization.dto.CredentialsDTO
import movies.spring.data.neo4j.exception.UnauthorizedException
import movies.spring.data.neo4j.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AuthorizationServicePasswordImpl (
        private val userRepository: UserRepository) : AuthorizationService
{

    @Transactional
    override fun authorize(request: CredentialsDTO): AuthorizationDTO
    {
        val user = userRepository.findByEmail(request.email)
        if (user == null || user.password != request.password)
        {
            throw UnauthorizedException("The provided request were not valid.")
        }
        return AuthorizationDTO.fromUser(user)
    }
}
