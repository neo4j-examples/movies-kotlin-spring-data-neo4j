package movies.spring.data.neo4j.api.endpoints.aspects

import org.apache.commons.logging.LogFactory
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.core.annotation.Order
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import movies.spring.data.neo4j.domain.model.persistent.queries.Principal
import movies.spring.data.neo4j.exception.UnauthorizedException

annotation class Authenticated

/**
 * Injects current user's profileId into controller method args annotated with @Authenticated.
 * This allows controllers to be unit tested without a dependency on SecurityContextHolder.
 */

@Component
@Aspect @Order(Integer.MAX_VALUE)
class AuthenticatedProfileIdAspect {

    val log = LogFactory.getLog(AuthenticatedProfileIdAspect::class.java)

    @Around("execution(* movies.spring.data.neo4j.api.endpoints.secured..*.*(..))")
    fun injectCurrentPrincipal(joinPoint: ProceedingJoinPoint): Any? {
        val profileId = principalOrThrow(joinPoint).profileId

        val newArgs = joinPoint.args.toMutableList()
        val methods = methods(joinPoint)
        for (i in methods.indices) {
            val method = methods[i]
            method
                    .filter { it.annotationClass.java == Authenticated::class.java }
                    .forEach { newArgs[i] = profileId }
        }

        val invocationResult = joinPoint.proceed(newArgs.toTypedArray())
        return invocationResult
    }


    private fun methods(joinPoint: ProceedingJoinPoint): Array<out Array<Annotation>> {
        val signature = joinPoint.signature as MethodSignature
        val methodName = signature.method.name
        val parameterTypes = signature.method.parameterTypes
        val annotations = joinPoint.target.javaClass.getMethod(methodName, *parameterTypes).parameterAnnotations
        return annotations
    }

    private fun principalOrThrow(joinPoint: ProceedingJoinPoint): Principal {
        val authentication = SecurityContextHolder.getContext().authentication
        val currentUser = when (authentication) {
            null -> throw UnauthorizedException("There is no current principal for injection into ${joinPoint.target}")
            else -> authentication.principal as Principal
        }
        return currentUser
    }

}



