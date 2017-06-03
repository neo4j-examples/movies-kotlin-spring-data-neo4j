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
class Role {

    @GraphId
    var id: Long? = null

    private val roles = ArrayList<String>()

    @StartNode
    var person: Person

    @EndNode
    var movie: Movie

    //Provide a default constructor for OGM
    constructor() {
        this.person = Person()
        this.movie = Movie()
    }

    constructor(movie: Movie, actor: Person) {
        this.movie = movie
        this.person = actor
    }

    fun addRoleName(name: String) {
        this.roles.add(name)
    }

}
