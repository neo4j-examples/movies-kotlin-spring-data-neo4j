package movies.spring.data.neo4j.domain.model.persistent.entities

import java.util.ArrayList

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.neo4j.ogm.annotation.EndNode
import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.RelationshipEntity
import org.neo4j.ogm.annotation.StartNode

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
@RelationshipEntity(type = "ACTED_IN")
class Role constructor(movie: Movie, actor: Person) {

    @GraphId
    var id: Long? = null

    @StartNode
    var person: Person = actor

    @EndNode
    var movie: Movie = movie

    val roles = ArrayList<String>()

    //Provide a default constructor for OGM
    constructor() : this(Movie(), Person())

    fun addRoleName(name: String) {
        this.roles.add(name)
    }

}
