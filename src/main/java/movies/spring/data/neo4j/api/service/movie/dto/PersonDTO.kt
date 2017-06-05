package movies.spring.data.neo4j.api.service.movie.dto

import movies.spring.data.neo4j.api.service.EntityDTOMapper
import movies.spring.data.neo4j.domain.model.persistent.entities.Person

data class PersonDTO(val name: String,
                     val born: Long?) {

    companion object : EntityDTOMapper<Person, PersonDTO> {

        override fun fromEntity(entity: Person) =
                PersonDTO(name = entity.name, born = entity.born)
    }
}

