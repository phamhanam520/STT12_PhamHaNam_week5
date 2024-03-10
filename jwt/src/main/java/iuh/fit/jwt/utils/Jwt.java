package iuh.fit.jwt.utils;

import iuh.fit.jwt.models.UserPrincial;

import java.util.logging.Logger;

public class Jwt {
    public static final Logger LOGGER = Logger.getLogger(Jwt.class.getName());
    public static final String USER="user";
    public static final String SECRET="some random";

    public static final long EXPIRATION_TIME=864_000_000; // 10 days
    public static String generateToken(UserPrincial userPrincial){
        String token = "";
        try {
            // build token for me
            token = io.jsonwebtoken.Jwts.builder()
                    .setSubject(userPrincial.getUsername())
                    .setExpiration(new java.util.Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
                    .compact();

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
