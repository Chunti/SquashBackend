package security;

import entities.User;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPrincipal implements Principal {

  private String email;
  private int role;

  /* Create a UserPrincipal, given the Entity class User*/
  public UserPrincipal(User user) {
    this.email = user.getEmail();
    this.role = user.getRole();
  }

  public UserPrincipal(String email, int role) {
    super();
    this.email = email;
    this.role = role;
  }

  @Override
  public String getName() {
    return email;
  }

  public boolean isUserInRole(String role) {
    if(this.role == Integer.parseInt(role)) return true;
    return false;
  }
}