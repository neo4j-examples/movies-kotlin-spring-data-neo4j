package movies.spring.data.neo4j.api.service.movie

import movies.spring.data.neo4j.api.service.movie.dto.GraphDTO
import movies.spring.data.neo4j.api.service.movie.dto.MovieDTO
import movies.spring.data.neo4j.domain.model.persistent.entities.Movie
import movies.spring.data.neo4j.domain.model.persistent.entities.Person
import movies.spring.data.neo4j.domain.model.persistent.entities.Role
import movies.spring.data.neo4j.repositories.MovieRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MovieService constructor(movieRepository: MovieRepository){

    val movieRepository = movieRepository

    @Transactional(readOnly = true)
    fun graph(limit: Int): GraphDTO {
        return GraphDTO.mapFromEntity(movieRepository.graph(limit))
    }

    @Transactional(readOnly = true)
    fun findByTitle(title: String): MovieDTO {
        return MovieDTO.fromEntity(movieRepository.findByTitle(title))
    }

    @Transactional(readOnly = true)
    fun findByTitleContaining(term: String): Collection<MovieDTO> {
        return MovieDTO.mapFromEntities(movieRepository.findByTitleContaining(term))
    }

    @Transactional
    fun save(dto: MovieDTO)
    {
        val movie = Movie(dto.title, dto.releasedYear, dto.tagLine)
        dto.roles.forEach {
            val person = Person(it.person.name)
            person.born = it.person.born

            val role = Role(movie, person)
            it.roles.forEach { roleName ->
                role.addRoleName(roleName)
            }
            movie.addRole(role)
        }
        movieRepository.save(movie)
    }



}
