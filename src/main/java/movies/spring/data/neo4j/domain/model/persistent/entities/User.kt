package movies.spring.data.neo4j.domain.model.persistent.entities

import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.NodeEntity
import java.util.*

@NodeEntity
class User
{

    @GraphId
    var graphId: Long? = null

    var uuid: String

    var applicationToken: String

    var firstName: String

    var lastName: String

    var email: String

    var password: String?

    var joined: Date

    var lastActive: Date

    //Provide a default constructor for OGM
    constructor()
    {
        this.uuid = UUID.randomUUID().toString()
        this.applicationToken = UUID.randomUUID().toString()
        this.firstName = ""
        this.lastName = ""
        this.lastActive = Date()
        this.joined = Date()
        this.email = ""
        this.password = null
    }


    constructor(firstName: String, lastName: String, email: String, password: String) : this()
    {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
        this.joined = Date()
    }


}
