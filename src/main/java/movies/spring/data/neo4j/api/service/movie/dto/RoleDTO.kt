package movies.spring.data.neo4j.api.service.movie.dto

import movies.spring.data.neo4j.api.service.EntityDTOMapper
import movies.spring.data.neo4j.domain.model.persistent.entities.Role

data class RoleDTO(val person: PersonDTO,
                   val roles: Collection<String>) {

    companion object : EntityDTOMapper<Role, RoleDTO> {

        override fun mapFromEntities(entities: Collection<Role>): Collection<RoleDTO> {
            return entities.map { RoleDTO.Companion.fromEntity(it) }
        }

        override fun fromEntity(movie: Role): RoleDTO {
            val roleDTO = RoleDTO(person = PersonDTO.fromEntity(movie.person), roles = movie.roles)
            return roleDTO
        }


    }
}

