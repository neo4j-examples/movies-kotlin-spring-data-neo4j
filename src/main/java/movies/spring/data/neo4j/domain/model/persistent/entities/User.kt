package movies.spring.data.neo4j.domain.model.persistent.entities

import org.neo4j.ogm.annotation.*
import java.util.*
import kotlin.collections.HashSet

@NodeEntity
open class User(@Id var uuid: String = UUID.randomUUID().toString(),
                @Index(unique = true) var applicationToken: String? = UUID.randomUUID().toString(),
                var firstName: String,
                var lastName: String,
                @Index(unique = true) var email: String,
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
