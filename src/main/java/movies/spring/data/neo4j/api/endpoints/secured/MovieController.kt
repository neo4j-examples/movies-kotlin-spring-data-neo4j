package movies.spring.data.neo4j.api.endpoints.secured

import movies.spring.data.neo4j.api.service.movie.dto.GraphDTO
import movies.spring.data.neo4j.api.service.movie.dto.MovieDTO
import movies.spring.data.neo4j.api.service.movie.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movies")
class MovieController @Autowired constructor(val movieService: MovieService) {

    @RequestMapping("/graph", method = arrayOf(RequestMethod.GET))
    fun graph(@RequestParam(required = false, defaultValue = "100") limit: Int?): GraphDTO {
        return movieService.graph(limit!!)
    }

    @RequestMapping("/by/title", method = arrayOf(RequestMethod.GET))
    fun byTitle(@RequestParam(required = true) title: String): MovieDTO {
        return movieService.findByTitle(title)
    }

    @RequestMapping("/by/title/containing", method = arrayOf(RequestMethod.GET))
    fun byTitleContaining(@RequestParam(required = true) title: String): Collection<MovieDTO> {
        return movieService.findByTitleContaining(title)
    }

    @RequestMapping("/", method = arrayOf(RequestMethod.PUT))
    fun save(@RequestBody dto: MovieDTO) {
        movieService.save(dto)
    }

}

