package dtos;

import entities.User;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {
    private final Integer id;
    private final String name;
    private final String email;
    private final Integer phone;
    private final Integer role;

    /*public UserDto(Integer id, String name, String email, Integer phone, Integer role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }*/
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.role = user.getRole();
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getPhone() {
        return phone;
    }

    public Integer getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.phone, entity.phone) &&
                Objects.equals(this.role, entity.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, role);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "email = " + email + ", " +
                "phone = " + phone + ", " +
                "role = " + role + ")";
    }
}
