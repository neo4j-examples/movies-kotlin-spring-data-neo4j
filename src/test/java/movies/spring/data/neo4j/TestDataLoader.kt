package movies.spring.data.neo4j

import movies.spring.data.neo4j.domain.model.persistent.entities.User
import movies.spring.data.neo4j.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * Create a test user for integration tests, until sign up feature implemented.
 */
@Component
class TestDataLoader @Autowired constructor(userRepo: UserRepository)
{
    private val userRepo: UserRepository = userRepo

    init
    {
        this.addTestUser()
    }



    @Transactional
    fun addTestUser()
    {
        val email = "jasper@appsquick.ly"
        val jasper = userRepo.findByEmail(email)
        if (jasper == null)
        {
            userRepo.save(User("Jasper", "Blues", email, "password"))
        }
    }

}