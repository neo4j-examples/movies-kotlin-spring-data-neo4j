package movies.spring.data.neo4j.repositories

import movies.spring.data.neo4j.domain.model.persistent.entities.Person
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository


@Repository
interface PersonRepository : PagingAndSortingRepository<Person, String>
