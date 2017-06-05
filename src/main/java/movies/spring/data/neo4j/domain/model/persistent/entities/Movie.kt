package movies.spring.data.neo4j.domain.model.persistent.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.neo4j.ogm.annotation.*
import java.util.*

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
@NodeEntity
class Movie constructor(title: String, releasedYear: Long, tagLine: String?) {

    @GraphId
    var id: Long? = null

    @Index(unique=true, primary = false)
    var uuid: String = UUID.randomUUID().toString()

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

    /**
     * Title is our primary index and merge key, therefore equals contract is based on it.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Movie) return false

        if (title != other.title) return false

        return true
    }

    /**
     * Title is our primary index and merge key, therefore hashCode contract is based on it.
     */
    override fun hashCode(): Int {
        return title.hashCode()
    }


}
