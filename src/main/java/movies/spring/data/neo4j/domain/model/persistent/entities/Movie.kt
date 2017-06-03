package movies.spring.data.neo4j.domain.model.persistent.entities

import java.util.ArrayList

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
@NodeEntity
class Movie {

    @GraphId
    var id: Long? = null

    var title: String? = null

    var released: Long? = null

    var tagline: String? = null

    @Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
    var roles = ArrayList<Role>()

    //Provide a default constructor for OGM
    constructor() {}

    constructor(title: String, released: Long) {

        this.title = title
        this.released = released
    }

    fun addRole(role: Role) {
        this.roles.add(role)
    }
}
