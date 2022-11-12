package utils;


import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User admin = new User(1001,"Zack Ottesen", "zo@pyra.dk",30329013,1,"Hej12345");
    User user = new User(1002, "Mogens Mogensen", "mogens@mogensen.dk", 30303030,2, "Hej12345");


    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();


    em.persist(user);
    em.persist(admin);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("Hej12345"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("Hej12345"));
    System.out.println("Created TEST Users");
   
  }
}
