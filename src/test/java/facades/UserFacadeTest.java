package facades;

import entities.User;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class UserFacadeTest {

    private static EntityManagerFactory emf;
    private static UserFacade userFacade;

    public UserFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
        userFacade = UserFacade.getUserFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.persist(new User(1001,"Zack Ottesen", "zo@pyra.dk",30329013,1,"Hej12345"));
            em.persist(new User(1002,"Rabia Rabiasen", "rabia@pyra.dk",20202020,2,"Hej12345"));
            em.persist(new User(1003,"Mathias Mathiasen", "mathias@pyra.dk",40404040,2,"Hej12345"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void verifyUser() throws Exception {
        String name = "Zack";
        String password = "Test123";

        userFacade.getVeryfiedUser(name,password);


    }


    

}
