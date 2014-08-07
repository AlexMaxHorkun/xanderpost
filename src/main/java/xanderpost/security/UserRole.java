package xanderpost.security;

import xanderpost.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRole implements org.springframework.security.core.GrantedAuthority {
    private String role;

    private int id;

    private UserRole parent;

    private List<UserRole> childRoles = new ArrayList<UserRole>();

    private List<User> users = new ArrayList<User>();

    public UserRole() {
    }

    public UserRole(String r) {
        setRole(r);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public UserRole getParent() {
        return parent;
    }

    public void setParent(UserRole parent) {
        this.parent = parent;
    }

    public List<UserRole> getChildRoles() {
        return childRoles;
    }

    public void setChildRoles(List<UserRole> childRoles) {
        this.childRoles = childRoles;
    }

    public String getAuthority() {
        return getRole();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
