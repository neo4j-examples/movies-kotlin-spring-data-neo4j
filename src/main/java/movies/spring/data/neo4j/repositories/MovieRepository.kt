package movies.spring.data.neo4j.repositories

import movies.spring.data.neo4j.domain.model.persistent.entities.Movie
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface MovieRepository : PagingAndSortingRepository<Movie, Long> {

    fun findByTitle(@Param("title") title: String): Movie?

    fun findByUuid(uuid: String): Movie?

    fun findByTitleContaining(title: String): Collection<Movie>

    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
    fun graph(@Param("limit") limit: Int): Collection<Movie>
}

