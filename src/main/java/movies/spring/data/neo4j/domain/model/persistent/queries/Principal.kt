

package movies.spring.data.neo4j.domain.model.persistent.queries

import org.springframework.data.neo4j.annotation.QueryResult


@QueryResult
class Principal constructor(applicationToken: String,
                                 profileId: String)
{

    var applicationToken: String = applicationToken
    var profileId: String = profileId


    //Provide a default constructor for OGM
    constructor() : this(applicationToken = "", profileId = "")


    override fun toString(): String
    {
        if (applicationToken.isNullOrEmpty())
        {
            return "Empty Principal"
        } else
        {
            return "Principal {token: $applicationToken, uuid: $profileId"
        }
    }

}