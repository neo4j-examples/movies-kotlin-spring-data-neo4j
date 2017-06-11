package movies.spring.data.neo4j.domain.model.persistent.entities

import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.Index
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.util.*
import kotlin.collections.HashSet

@NodeEntity
class User(@GraphId var graphId: Long? = null,
           @Index(unique = true, primary = true) var uuid: String = UUID.randomUUID().toString(),
           @Index(unique = true, primary = false) var applicationToken: String? = null,
           var firstName: String,
           var lastName: String,
           @Index(unique = true, primary = false) var email: String,
           var password: String?,
           var joined: Date = Date(),
           var lastActive: Date = Date(),
           @Relationship(type = "LIKES", direction = Relationship.OUTGOING)
           var likes: MutableSet<Movie> = HashSet()) {

    fun addLikeInteraction(movie: Movie) {
        //TODO: Bug - OGM should set the property as an empty set
        if (likes == null) {
            likes = HashSet()
        }
        likes.add(movie)
    }


}
