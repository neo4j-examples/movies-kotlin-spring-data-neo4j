package movies.spring.data.neo4j.api.endpoints.secured

import movies.spring.data.neo4j.api.endpoints.aspects.Authenticated
import movies.spring.data.neo4j.api.service.user.dto.UserDTO
import movies.spring.data.neo4j.api.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(userService : UserService)
{
    private val userService : UserService = userService

    @RequestMapping("/mine", method = arrayOf(RequestMethod.GET))
    open fun getMyProfile(@Authenticated profileId: String): UserDTO
    {
        return userService.getMyUser(profileId)
    }


}