package movies.spring.data.neo4j.exception

class NotFoundException : RuntimeException {

    constructor(message: String?) : super(message)

}
