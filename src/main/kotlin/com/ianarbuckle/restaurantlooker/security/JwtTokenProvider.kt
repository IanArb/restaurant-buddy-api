package com.ianarbuckle.restaurantlooker.security

import com.ianarbuckle.restaurantlooker.authentication.model.Role
import com.ianarbuckle.restaurantlooker.authentication.service.CustomUserDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import java.util.Base64.getEncoder
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

/**
 * @author ianarbuckle on 22/09/2019.
 */
@Component
class JwtTokenProvider {

    @Value("\${security.token.secret-key}")
    private lateinit var secretKey: String

    //30 days
    private var validityInMilliseconds: Long = 864_000_000

    private val refreshValidityInMilliseconds: Long = 864_000_000

    @Autowired
    private lateinit var userDetailsService: CustomUserDetailsService

    @PostConstruct
    protected fun init() {
        secretKey = getEncoder().encodeToString(secretKey.toByteArray())
    }

    fun createToken(uuid: String, set: Set<Role>): String {
        val claims = Jwts.claims().setSubject(uuid)
        claims["roles"] = set
        val now = Date()
        val validity = Date(now.time + TimeUnit.DAYS.toMillis(validityInMilliseconds))
        return buildJwtToken(claims, now, validity)
    }

    fun createRefreshToken(uuid: String, set: Set<Role>): String {
        val claims = Jwts.claims().setSubject(uuid)
        claims["roles"] = set
        val now = Date()
        val validity = Date(now.time + TimeUnit.DAYS.toMillis(validityInMilliseconds))
        return buildJwtToken(claims, now, validity)
    }

    private fun buildJwtToken(claims: Claims?, now: Date, validity: Date): String {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = this.userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails?.authorities)
    }

    fun getUsername(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: JwtException) {
            throw JwtException("Expired or invalid token")
        } catch (e: IllegalArgumentException) {
            throw JwtException("Expired or invalid token")
        }
    }
}