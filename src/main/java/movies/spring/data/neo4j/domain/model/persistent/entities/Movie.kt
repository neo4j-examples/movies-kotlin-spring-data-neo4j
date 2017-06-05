package movies.spring.data.neo4j.domain.model.persistent.entities

import java.util.ArrayList

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.neo4j.ogm.annotation.*

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
@NodeEntity
class Movie constructor(title: String, releasedYear: Long, tagLine: String?) {

    @GraphId
    var id: Long? = null

    @Index(unique=true, primary = true)
    var title: String = title

    @Property(name = "releasedYear")
    var releasedYear: Long = releasedYear

    var tagline: String? = tagLine

    @Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
    var roles = ArrayList<Role>()

    //Provide a default constructor for OGM
    constructor() : this(title = "", releasedYear = 0, tagLine = null)

    constructor(title: String, releasedYear: Long) : this(title, releasedYear, null)

    fun addRole(role: Role) {
        this.roles.add(role)
    }
}
