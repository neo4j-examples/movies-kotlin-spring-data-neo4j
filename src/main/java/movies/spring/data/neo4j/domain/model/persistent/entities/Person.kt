package movies.spring.data.neo4j.domain.model.persistent.entities


import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.util.*


@NodeEntity
class Person(@Id var name: String,
             var born: Long,
             @Relationship(type = "ACTED_IN") var movies: List<Movie> = ArrayList())
