package movies.spring.data.neo4j.domain.model.persistent.entities


import java.util.ArrayList

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship


@NodeEntity
class Person {

    @GraphId
    var id: Long? = null

    var name: String? = null

    var born: Long? = null

    @Relationship(type = "ACTED_IN")
    var movies: List<Movie> = ArrayList()

    //Provide a default constructor for OGM
    constructor() {}

    constructor(name: String) {
        this.name = name
    }
}
