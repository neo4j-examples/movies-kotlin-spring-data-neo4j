package movies.spring.data.neo4j.api.service.user

import movies.spring.data.neo4j.api.service.user.dto.UserDTO
import movies.spring.data.neo4j.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class UserService @Autowired constructor(userRepo: UserRepository)
{
    private val userRepository: UserRepository = userRepo


    @Transactional
    fun getMyUser(profileId: String): UserDTO
    {
        val user = userRepository.findByUuid(profileId)!!
        user.lastActive = Date()
        userRepository.save(user)

        return UserDTO.fromUser(user)
    }
}