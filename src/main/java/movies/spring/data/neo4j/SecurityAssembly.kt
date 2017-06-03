package movies.spring.data.neo4j

import movies.spring.data.neo4j.infrastructure.security.TokenAuthenticationFilter
import movies.spring.data.neo4j.infrastructure.security.TokenAuthenticationProvider
import movies.spring.data.neo4j.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityAssembly : WebSecurityConfigurerAdapter() {

    @Autowired @Value("@{api.key}") lateinit var apiKey: String

    @Autowired lateinit var profileRepo: UserRepository


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(TokenAuthenticationFilter(authenticationManager()),
                BasicAuthenticationFilter::class.java)

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(*arrayOf("/authorization/**", "/public/**")).permitAll()
                .antMatchers("/**").authenticated()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.authenticationProvider(tokenAuthenticationProvider())
    }

    @Bean
    fun tokenAuthenticationProvider(): AuthenticationProvider {
        return TokenAuthenticationProvider(profileRepo, apiKey)
    }


}