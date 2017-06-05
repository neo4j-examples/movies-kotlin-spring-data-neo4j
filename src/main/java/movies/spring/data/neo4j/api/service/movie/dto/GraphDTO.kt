package movies.spring.data.neo4j.api.service.movie.dto

data class GraphDTO(val nodes: List<NodeDTO>,
                    val links: List<LinkDTO>) {

    companion object {
        fun mapFromEntity(movies : Collection<movies.spring.data.neo4j.domain.model.persistent.entities.Movie>) : movies.spring.data.neo4j.api.service.movie.dto.GraphDTO
        {
            val nodes = java.util.ArrayList<NodeDTO>()
            val links = java.util.ArrayList<LinkDTO>()
            var i = 0
            val result = movies.iterator()
            while (result.hasNext()) {
                val movie = result.next()
                nodes.add(NodeDTO(title = movie.title, label = "movie"))
                val target = i
                i++
                for (role in movie.roles) {
                    val actor = NodeDTO(title = role.person.name, label = "actor")
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

}