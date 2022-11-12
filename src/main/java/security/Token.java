package security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Date;
import java.util.List;


// We have pasted already existing code from LoginEndPoint and pasted in this new class we created
// This is a class which creates a token
public class Token {

    public static final int TOKEN_EXPIRE_TIME = 1000 * 60 * 30; //30 min
    private final String TOKEN;

    public Token(String username, int roles) throws JOSEException {
        TOKEN = createToken(username, roles);
    }

    public static String createToken(String email, int role) throws JOSEException {

        JWSSigner signer = new MACSigner(SharedSecret.getSharedKey());
        Date date = new Date();
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                //.claim("email", email)
                .claim("role", role)
                .issueTime(date)
                .expirationTime(new Date(date.getTime() + TOKEN_EXPIRE_TIME))
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    public String toString() {
        return TOKEN;
    }
}