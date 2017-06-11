package movies.spring.data.neo4j.api.service.authorization.dto

import movies.spring.data.neo4j.api.service.EntityDTOMapper
import movies.spring.data.neo4j.domain.model.persistent.entities.User

data class AuthorizationDTO(
        val accessToken: String,
        val profileId: String) {

    companion object : EntityDTOMapper<User, AuthorizationDTO> {

        override fun fromEntity(entity: User) = AuthorizationDTO(
                accessToken = entity.applicationToken!!,
                profileId = entity.uuid)

    }
}



