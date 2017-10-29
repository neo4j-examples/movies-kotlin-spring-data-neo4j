package movies.spring.data.neo4j.api.service.movie.dto

import movies.spring.data.neo4j.api.service.EntityDTOMapper
import movies.spring.data.neo4j.domain.model.persistent.entities.Movie

class MovieDTO(val title: String,
               val uuid: String? = null,
               val releasedYear: Long,
               val tagLine: String?,
               val roles: Collection<RoleDTO>) {

    companion object : EntityDTOMapper<Movie, MovieDTO> {

        override fun fromEntity(entity: Movie) = MovieDTO(
                title = entity.title,
                releasedYear = entity.releasedYear,
                tagLine = entity.tagLine,
                roles = RoleDTO.mapFromEntities(entity.roles))
    }

}


