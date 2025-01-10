package bizi.bizi.service
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetails
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.Jwts
import java.time.Duration
import java.time.Instant
import java.util.Date 

@Service
class JwtService {
    private val secretKey = "randomsec44reasdghjg65168satmano@@!!" 
    private val validity = Duration.ofHours(24)

    fun generateToken(user: UserDetails): String {
        val now = Instant.now()
        return Jwts.builder()
            .setSubject(user.username)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(validity)))
            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .compact()
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
                .build()
                .parseClaimsJws(token)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun getLoginFromToken(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }
}
