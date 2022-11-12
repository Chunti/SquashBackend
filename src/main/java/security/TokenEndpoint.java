package security;

import com.google.gson.Gson;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import entities.User;
import facades.UserFacade;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.text.ParseException;
import java.util.Date;

@Path("verify")
public class TokenEndpoint {



    @Context
    SecurityContext securityContext;

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final UserFacade USER_FACADE = UserFacade.getUserFacade(EMF);
    private static final Gson GSON = new Gson();

    //http://localhost:8080/api/verify GET
    //when GETTING with token: "bcrypt" it should get admin and admins roles or token is not valid
    //få useren til at blive logget ud efter 30 min , skal vi gøre her i denne ressource
    //token har bare username og expiration date

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifyToken(@HeaderParam("x-access-token") String token) throws AuthenticationException {
        System.out.println("Token: " + token);
        //x-access-token is replacing token. because it is already written in apiFacade.js frontend
        //@HeaderParam takes one of the requests headers and use it to verify token - > expired or not

        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SharedSecret.getSharedKey());
            if (signedJWT.verify(verifier)) {
                if (new Date().getTime() > signedJWT.getJWTClaimsSet().getExpirationTime().getTime()) {
                    throw new AuthenticationException("Your Token is no longer valid");
                }
            }
            System.out.println("Token is valid");
            String username = signedJWT.getJWTClaimsSet().getSubject();
            User user = USER_FACADE.getUser(username); // <--- getUser. Added a method in userFacade
            return Response.ok(GSON.toJson(new Token(username, user.getRole()).toString())).build();
        } catch (ParseException e) {
            System.out.println("ParseException:");
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            System.out.println("JOSEException:");
            throw new RuntimeException(e);
        }
    }



}
