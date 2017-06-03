package movies.spring.data.neo4j

import movies.spring.data.neo4j.domain.model.persistent.entities.User
import org.neo4j.ogm.session.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import movies.spring.data.neo4j.repositories.UserRepository
import movies.spring.data.neo4j.utils.query

@Component
class TestDataLoader @Autowired constructor(session: Session, userRepo: UserRepository)
{
    private val session: Session = session
    private val userRepo: UserRepository = userRepo

    init
    {
        this.clearUsers()
        this.installConstraints()
        this.loadTestData()
    }

    @Transactional
    open fun clearUsers()
    {
        userRepo.deleteAll()
    }

    @Transactional
    fun installConstraints()
    {
        session.query("CREATE CONSTRAINT ON (u:User) ASSERT u.uuid IS UNIQUE;")
        session.query("CREATE CONSTRAINT ON (u:User) ASSERT u.applicationToken IS UNIQUE;")
        session.query("CREATE CONSTRAINT ON (u:User) ASSERT u.email IS UNIQUE;")
    }

    @Transactional
    fun loadTestData()
    {
        val email = "jasper@appsquick.ly"
        val jasper = userRepo.findByEmail(email)
        if (jasper == null)
        {
            userRepo.save(User("Jasper", "Blues", email, "password"))
        }
    }

}