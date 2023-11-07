package security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "secret-key";
    private static final long EXPIRATION_TIME = 864000000; // 10 days in milliseconds

    public String generateJWT(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean isValidJWT(String jwt) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException e) {
            return false;
        }
    }


}

