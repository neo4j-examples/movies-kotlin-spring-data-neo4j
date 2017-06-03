package movies.spring.data.neo4j.exception


class UnauthorizedException: RuntimeException
{
    constructor(message: String?) : super(message)

}