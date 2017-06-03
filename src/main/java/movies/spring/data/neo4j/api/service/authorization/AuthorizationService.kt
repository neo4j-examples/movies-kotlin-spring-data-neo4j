package movies.spring.data.neo4j.api.service.authorization

interface AuthorizationService
{
    fun authorize(request: CredentialsDTO): AuthorizationDTO
}