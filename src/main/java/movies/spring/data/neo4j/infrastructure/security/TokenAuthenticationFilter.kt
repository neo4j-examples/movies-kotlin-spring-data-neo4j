package movies.spring.data.neo4j.infrastructure.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenAuthenticationFilter(private val authenticationManager: AuthenticationManager)
    : GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        val token: String? = httpRequest.getHeader("Access-Token")
        val apiKey: String? = httpRequest.getHeader("API-Key")

        try {
            if (!token.isNullOrEmpty() && !apiKey.isNullOrEmpty()) {
                processTokenAuthentication(token!!, apiKey!!)
            }
            chain.doFilter(request, response)
        } catch (internalAuthenticationServiceException: InternalAuthenticationServiceException) {
            SecurityContextHolder.clearContext()
            logger.error("Internal authentication service exception", internalAuthenticationServiceException)
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
        } catch (authenticationException: AuthenticationException) {
            SecurityContextHolder.clearContext()
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.message)
        }
    }

    private fun processTokenAuthentication(token: String, apiKey: String) {
        val authCredentials = SessionCredentials(apiKey, token)
        val requestAuthentication = PreAuthenticatedAuthenticationToken(authCredentials, authCredentials)
        val resultOfAuthentication = tryToAuthenticate(requestAuthentication)
        SecurityContextHolder.getContext().authentication = resultOfAuthentication
    }

    private fun tryToAuthenticate(requestAuthentication: Authentication): Authentication {
        val responseAuthentication = authenticationManager.authenticate(requestAuthentication)
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated) {
            throw InternalAuthenticationServiceException("Unable to authenticate Domain User for provided credentials")
        }
        return responseAuthentication
    }

}
