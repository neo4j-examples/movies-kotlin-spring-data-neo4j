package movies.spring.data.neo4j

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@SpringBootApplication
@EntityScan("movies.spring.data.neo4j.domain.model.persistent")
class ApplicationAssembly
{

    companion object
    {
        @JvmStatic fun main(args: Array<String>)
        {
            SpringApplication.run(ApplicationAssembly::class.java, *args)
        }
    }

    constructor() : super()

    @Bean
    fun kotlinPropertyConfigurer(): PropertySourcesPlaceholderConfigurer
    {
        val propertyConfigurer = PropertySourcesPlaceholderConfigurer()
        propertyConfigurer.setPlaceholderPrefix("@{")
        propertyConfigurer.setPlaceholderSuffix("}")
        propertyConfigurer.setIgnoreUnresolvablePlaceholders(true)
        return propertyConfigurer
    }

    @Bean
    fun defaultPropertyConfigurer(): PropertySourcesPlaceholderConfigurer
    {
        return PropertySourcesPlaceholderConfigurer()
    }

    @Bean
    fun mapperConfigurer(): Jackson2ObjectMapperBuilder
    {
        val builder = Jackson2ObjectMapperBuilder()
        builder.serializationInclusion(JsonInclude.Include.NON_NULL)
        builder.failOnUnknownProperties(true)
        builder.featuresToDisable(*arrayOf(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
        builder.indentOutput(true)
        builder.modules(listOf(KotlinModule()))
        return builder
    }
}

