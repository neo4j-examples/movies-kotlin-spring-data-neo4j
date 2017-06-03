package movies.spring.data.neo4j.api.service.movie

import movies.spring.data.neo4j.domain.model.persistent.entities.Movie
import movies.spring.data.neo4j.repositories.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MovieService {

    @Autowired internal var movieRepository: MovieRepository? = null

    @Transactional(readOnly = true)
    fun graph(limit: Int): GraphDTO {
        val result = movieRepository!!.graph(limit)
        return toD3Format(result)
    }

    private fun toD3Format(movies: Collection<Movie>): GraphDTO {
        val nodes = ArrayList<NodeDTO>()
        val links = ArrayList<LinkDTO>()
        var i = 0
        val result = movies.iterator()
        while (result.hasNext()) {
            val movie = result.next()
            nodes.add(NodeDTO(title = movie.title!!, label = "movie"))
            val target = i
            i++
            for (role in movie.roles) {
                val actor = NodeDTO(title = role.person.name!!, label = "actor")
                var source = nodes.indexOf(actor)
                if (source == -1) {
                    nodes.add(actor)
                    source = i++
                }
                links.add(LinkDTO(source, target))
            }
        }
        return GraphDTO(nodes, links)
    }


}
