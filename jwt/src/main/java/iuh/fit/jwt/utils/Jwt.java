package iuh.fit.jwt.utils;

import io.jsonwebtoken.Claims;
import iuh.fit.jwt.models.UserPrincial;

import java.util.Base64;
import java.util.logging.Logger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import javax.xml.bind.DatatypeConverter;
import org.springframework.context.annotation.Bean;


public class Jwt {
    public static final Logger LOGGER = Logger.getLogger(Jwt.class.getName());
    public static final String USER="user";
    public static final String SECRET="some random";

    public static final long EXPIRATION_TIME=864_000_000; // 10 days
    public static String generateToken(UserPrincial userPrincial){
        String token = "";
        try {

            token = Jwts.builder()
                    .setSubject("user")
                    .claim("user", userPrincial.getUsername())
                    .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                    .setExpiration(new java.util.Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return token;
    }

    private static Claims getClaims(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
    public static UserPrincial getUserFromToken(String token){
        UserPrincial userPrincial = null;
        try {
            Claims claims = getClaims(token);
            userPrincial = (UserPrincial) claims.get(USER);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return userPrincial;
    }

    public static boolean isTokenExpired(String token){
        try {
            Claims claims = getClaims(token);
            System.out.println(claims.getExpiration());
            return claims.getExpiration().before(new java.util.Date());
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return true;
    }
}
