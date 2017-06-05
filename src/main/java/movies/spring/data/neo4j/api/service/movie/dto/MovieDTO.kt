package movies.spring.data.neo4j.api.service.movie.dto

import movies.spring.data.neo4j.api.service.EntityDTOMapper
import movies.spring.data.neo4j.domain.model.persistent.entities.Movie

class MovieDTO(val title: String,
               val releasedYear: Long,
               val tagLine: String?,
               val roles: Collection<RoleDTO>) {

    companion object : EntityDTOMapper<Movie, MovieDTO> {

        override fun mapFromEntities(entities: Collection<Movie>): Collection<MovieDTO> {
            return entities.map { fromEntity(it) }
        }

        override fun fromEntity(movie: Movie): MovieDTO {
            return MovieDTO(title = movie.title,
                    releasedYear = movie.releasedYear,
                    tagLine = movie.tagline,
                    roles = RoleDTO.Companion.mapFromEntities(movie.roles))
        }

    }

}


