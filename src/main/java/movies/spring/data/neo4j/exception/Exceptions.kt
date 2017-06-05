package movies.spring.data.neo4j.exception

class UnauthorizedException(message: String) : RuntimeException(message)
class NotFoundException(message: String) : RuntimeException(message) 