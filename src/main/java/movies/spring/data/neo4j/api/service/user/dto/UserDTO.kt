package movies.spring.data.neo4j.api.service.user.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import movies.spring.data.neo4j.domain.model.persistent.entities.User


@JsonInclude(Include.NON_NULL)
data class UserDTO(
        val uuid: String,
        val firstName: String,
        val lastName: String,
        val email: String)
{
    companion object
    {

        fun fromUser(user: User): UserDTO
        {
            return UserDTO(uuid = user.uuid, firstName = user.firstName, lastName = user.lastName,
                    email = user.email)
        }
    }
}



