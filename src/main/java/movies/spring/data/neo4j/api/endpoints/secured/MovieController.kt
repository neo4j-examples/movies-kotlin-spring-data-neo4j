package movies.spring.data.neo4j.api.endpoints.secured

import movies.spring.data.neo4j.api.service.movie.GraphDTO
import movies.spring.data.neo4j.api.service.movie.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieController @Autowired
constructor(val movieService: MovieService) {

    @RequestMapping("/graph")
    fun graph(@RequestParam(value = "limit", required = false, defaultValue = "100") limit: Int?)
            : GraphDTO {
        return movieService.graph(limit!!)
    }
}

