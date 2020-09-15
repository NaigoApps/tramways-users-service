package it.tramways.users;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import it.tramways.core.model.TramwaysUserDetails;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class TokenManager {

    private static final String UUID_CLAIM = "uuid";
    private static final String USER_CLAIM = "username";
    private static final String PASSWORD_CLAIM = "password";
    private static final String ROLES_CLAIM = "roles";

    private static final String PASSWORD = "tramways";

    public TramwaysUserDetails token2UserDetail(String jwt) {
        DecodedJWT decodedJWT = decodeJWT(jwt);
        if (decodedJWT != null) {
            return new TramwaysUserDetails(
                decodedJWT.getClaim(UUID_CLAIM).asString(),
                decodedJWT.getClaim(USER_CLAIM).asString(),
                decodedJWT.getClaim(PASSWORD_CLAIM).asString(),
                Arrays.stream(decodedJWT.getClaim(ROLES_CLAIM).asArray(String.class))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList())
            );
        }
        return null;
    }

    private DecodedJWT decodeJWT(String jwt) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("tramways")).build();
            return verifier.verify(jwt);
        } catch (JWTDecodeException ex) {
            return null;
        }
    }

    public String requestToken(TramwaysUserDetails details) {
        return createJWT(details);
    }

    private String createJWT(TramwaysUserDetails user) {
        //@formatter:off
		return JWT.create()
				.withExpiresAt(nextDay())
				.withClaim(UUID_CLAIM, user.getUuid())
				.withClaim(USER_CLAIM, user.getUsername())
				.withClaim(PASSWORD_CLAIM, user.getPassword())
				.withArrayClaim(ROLES_CLAIM, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
				.sign(Algorithm.HMAC256(PASSWORD));
		//@formatter:on
    }

    private Date nextDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
}
