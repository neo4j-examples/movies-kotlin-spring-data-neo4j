package movies.spring.data.neo4j.domain.model.persistent.entities

import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Property
import org.neo4j.ogm.annotation.Relationship

@NodeEntity
class Movie(@Id var title: String,
            @Property(name = "releasedYear") var releasedYear: Long,
            @Property(name = "tagline") var tagLine: String? = null,
            @Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
            var roles: ArrayList<Role> = ArrayList()) {

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
