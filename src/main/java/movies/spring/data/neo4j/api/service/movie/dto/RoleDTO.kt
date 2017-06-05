package movies.spring.data.neo4j.api.service.movie.dto

import movies.spring.data.neo4j.api.service.EntityDTOMapper
import movies.spring.data.neo4j.domain.model.persistent.entities.Role

data class RoleDTO(val person: PersonDTO,
                   val roles: Collection<String>) {

    companion object : EntityDTOMapper<Role, RoleDTO> {

        override fun fromEntity(entity: Role): RoleDTO =
                RoleDTO(person = PersonDTO.fromEntity(entity.person), roles = entity.roles)


    }
}

