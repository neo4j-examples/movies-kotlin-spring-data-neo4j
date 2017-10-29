package movies.spring.data.neo4j.repositories


import movies.spring.data.neo4j.domain.model.persistent.entities.User
import movies.spring.data.neo4j.domain.model.persistent.queries.Principal
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>
{

    fun findByUuid(id: String): User?

    @Cacheable("AuthorizationCache")
    @Query("MATCH (n:User {applicationToken: {0} }) RETURN n.uuid as profileId, n.applicationToken as applicationToken, n.roles as roles;")
    fun findByApplicationToken(token: String): Principal?

    fun findByEmail(email: String): User?

}

