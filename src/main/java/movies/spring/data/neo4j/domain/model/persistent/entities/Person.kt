package movies.spring.data.neo4j.domain.model.persistent.entities


import java.util.ArrayList

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.Index
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship


@NodeEntity
class Person constructor(name: String, born: Long?) {

    @GraphId
    var id: Long? = null

    @Index(unique=true, primary = true)
    var name = name

    var born = born

    @Relationship(type = "ACTED_IN")
    var movies: List<Movie> = ArrayList()

    //Provide a default constructor for OGM
    constructor() : this(name = "", born = null)

    constructor(name: String) : this(name = name, born = null)

}
