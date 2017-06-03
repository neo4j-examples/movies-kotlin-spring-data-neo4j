package movies.spring.data.neo4j.api.service.movie

data class GraphDTO(val nodes: List<NodeDTO>, val links: List<LinkDTO>)