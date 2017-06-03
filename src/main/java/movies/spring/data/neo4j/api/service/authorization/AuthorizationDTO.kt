package movies.spring.data.neo4j.api.service.authorization

import movies.spring.data.neo4j.domain.model.persistent.entities.User

data class AuthorizationDTO(
        val accessToken: String,
        val profileId: String) {

    companion object {

        fun fromUser(user: User): AuthorizationDTO {
            return AuthorizationDTO(accessToken = user.applicationToken, profileId = user.uuid)
        }

    }
}



