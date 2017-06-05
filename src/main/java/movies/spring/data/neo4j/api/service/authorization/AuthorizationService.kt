package movies.spring.data.neo4j.api.service.authorization

import movies.spring.data.neo4j.api.service.authorization.dto.AuthorizationDTO
import movies.spring.data.neo4j.api.service.authorization.dto.CredentialsDTO

interface AuthorizationService
{
    fun authorize(request: CredentialsDTO): AuthorizationDTO
}