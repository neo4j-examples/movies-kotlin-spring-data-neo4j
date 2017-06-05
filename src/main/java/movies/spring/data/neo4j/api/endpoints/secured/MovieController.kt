package movies.spring.data.neo4j.api.endpoints.secured

import movies.spring.data.neo4j.api.endpoints.aspects.Authenticated
import movies.spring.data.neo4j.api.service.movie.MovieService
import movies.spring.data.neo4j.api.service.movie.dto.MovieDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movies")
class MovieController constructor(private val movieService: MovieService) {

    @GetMapping("/graph")
    fun graph(@RequestParam(required = false, defaultValue = "100") limit: Int?) =
            movieService.graph(limit!!)

    @GetMapping("/by/title")
    fun byTitle(@RequestParam(required = true) title: String) = movieService.findByTitle(title)

    @GetMapping("/by/title/containing")
    fun byTitleContaining(@RequestParam(required = true) title: String) =
            movieService.findByTitleContaining(title)

    @PutMapping("/")
    fun save(@RequestBody dto: MovieDTO) = movieService.save(dto)

    @PutMapping("/{uuid}/like")
    fun addLikeInteraction(@PathVariable("uuid") uuid: String, @Authenticated userUuid: String) =
            movieService.addLikeInteractionTo(uuid, forUserUserUuid = userUuid)


}

