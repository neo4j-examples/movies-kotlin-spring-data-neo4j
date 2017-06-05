package movies.spring.data.neo4j.api.endpoints.pub


import movies.spring.data.neo4j.api.service.authorization.AuthorizationService
import movies.spring.data.neo4j.api.service.authorization.dto.AuthorizationDTO
import movies.spring.data.neo4j.api.service.authorization.dto.CredentialsDTO
import movies.spring.data.neo4j.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
class AuthorizationController
{
    val authService: AuthorizationService
    val profileRepo: UserRepository

    @Autowired private constructor(authService: AuthorizationService, profileRepo: UserRepository)
    {
        this.authService = authService
        this.profileRepo = profileRepo
    }

    @RequestMapping("/authorization", method = arrayOf(RequestMethod.POST))
    open fun authorize(@RequestBody credentials: CredentialsDTO): AuthorizationDTO
    {
        return authService.authorize(credentials)
    }



}