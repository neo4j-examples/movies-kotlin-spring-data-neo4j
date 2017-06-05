package movies.spring.data.neo4j.repositories

import org.junit.Assert.*

import movies.spring.data.neo4j.domain.model.persistent.entities.Movie
import movies.spring.data.neo4j.domain.model.persistent.entities.Person
import movies.spring.data.neo4j.domain.model.persistent.entities.Role
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.neo4j.ogm.session.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieRepositoryTest {

    @Autowired lateinit var session: Session

    @Autowired lateinit var movieRepository: MovieRepository

    @Autowired lateinit var personRepository: PersonRepository

    @Before
    fun setUp() {
        movieRepository.deleteAll()

        val matrix = Movie(title = "The Matrix", releasedYear = 1999)

        movieRepository.save(matrix)

        val keanu = Person("Keanu Reeves")

        personRepository.save(keanu)

        val neo = Role(matrix, keanu)
        neo.addRoleName("Neo")

        matrix.addRole(neo)

        movieRepository.save(matrix)
    }


    /**
     * Test of findByTitle method, of class MovieRepository.
     */
    @Test
    fun testFindByTitle() {

        val title = "The Matrix"
        val result = movieRepository.findByTitle(title)
        assertNotNull(result)
        assertEquals(1999L, result!!.releasedYear)
    }

    @Test
    fun findByUUid()
    {
        val foobar = Movie(title = "Foobar", releasedYear = 2017)
        movieRepository.save(foobar)

        assertNotNull(movieRepository.findByUuid(foobar.uuid))
    }

    /**
     * Test of findByTitleContaining method, of class MovieRepository.
     */
    @Test
    fun testFindByTitleContaining() {
        val title = "Matrix"
        val result = movieRepository.findByTitleContaining(title)
        assertNotNull(result)
        assertEquals(1, result.size.toLong())
    }

    /**
     * Test of graph method, of class MovieRepository.
     */
    @Test
    fun testGraph() {
        val graph = movieRepository.graph(5)

        assertEquals(1, graph.size.toLong())

        val movie = graph.iterator().next()

        assertEquals(1, movie.roles.size.toLong())

        assertEquals("The Matrix", movie.title)
        assertEquals("Keanu Reeves", movie.roles.iterator().next().person!!.name)
    }
}
