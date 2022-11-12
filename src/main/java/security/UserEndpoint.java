package security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import dtos.UserDto;
import entities.User;
import errorhandling.API_Exception;
import errorhandling.GenericExceptionMapper;
import facades.UserFacade;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;


@Path("user")
public class UserEndpoint {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private final EntityManager em = EMF.createEntityManager();
    public static final UserFacade USER_FACADE = UserFacade.getUserFacade(EMF);
    private final Gson GSON = new Gson();

    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(String jsonString) throws API_Exception {
        String name;
        int userNumber;
        String email;
        int phoneNumber;
        String password;
        try {
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            name = json.get("name").getAsString();
            userNumber = json.get("userNumber").getAsInt();
            email = json.get("email").getAsString();
            phoneNumber = json.get("phoneNumber").getAsInt();
            password = json.get("password").getAsString();
        } catch (Exception e) {
            throw new API_Exception("Malformed JSON Suplied",400,e);
        }

        User newUser = new User(userNumber,name, email,phoneNumber,2,password);
        em.getTransaction().begin();
        em.persist(newUser);
        em.getTransaction().commit();
    }

    @Path("delete")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUser(String jsonString) throws API_Exception {
        int id;

        try{
            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
            id = json.get("userNumber").getAsInt();

        }catch (Exception e){
            throw new API_Exception("Malformed JSON Suplied",400,e);
        }

        try{
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if(user != null) em.remove(user);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        em.getTransaction().begin();
        User user = em.find(User.class,id);
        em.getTransaction().commit();
        em.close();
        System.out.println(user.toString());
        UserDto userDto = new UserDto(user);
        return Response.ok(GSON.toJson(userDto)).build();
    }
}
