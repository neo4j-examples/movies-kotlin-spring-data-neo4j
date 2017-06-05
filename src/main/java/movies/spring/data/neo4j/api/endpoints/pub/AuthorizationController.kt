package movies.spring.data.neo4j.api.endpoints.pub


import movies.spring.data.neo4j.api.service.authorization.AuthorizationService
import movies.spring.data.neo4j.api.service.authorization.dto.AuthorizationDTO
import movies.spring.data.neo4j.api.service.authorization.dto.CredentialsDTO
import movies.spring.data.neo4j.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
class AuthorizationController(val authService: AuthorizationService) {

    @PostMapping("/authorization")
    fun authorize(@RequestBody credentials: CredentialsDTO) = authService.authorize(credentials)

}