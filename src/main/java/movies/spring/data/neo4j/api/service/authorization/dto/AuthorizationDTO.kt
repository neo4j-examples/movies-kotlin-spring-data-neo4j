package movies.spring.data.neo4j.api.service.authorization.dto

data class AuthorizationDTO(
        val accessToken: String,
        val profileId: String) {

    companion object {

        fun fromUser(user: movies.spring.data.neo4j.domain.model.persistent.entities.User): movies.spring.data.neo4j.api.service.authorization.dto.AuthorizationDTO {
            return movies.spring.data.neo4j.api.service.authorization.dto.AuthorizationDTO(accessToken = user.applicationToken, profileId = user.uuid)
        }

    }
}



