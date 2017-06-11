package movies.spring.data.neo4j.domain.model.persistent.entities

import java.util.ArrayList

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.neo4j.ogm.annotation.*

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
@RelationshipEntity(type = "ACTED_IN")
class Role(@GraphId var id: Long? = null,
           @StartNode var person: Person,
           @EndNode var movie: Movie,
           var roles: ArrayList<String> = ArrayList()) {

    fun addRoleName(name: String) {
        this.roles.add(name)
    }

}
