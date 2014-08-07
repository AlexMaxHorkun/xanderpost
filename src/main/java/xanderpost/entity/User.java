package xanderpost.entity;

import org.springframework.security.core.userdetails.UserDetails;
import xanderpost.security.UserRole;

import java.util.*;

public class User implements UserDetails {
    private long id;

    private String email;

    private String password;

    private List<UserRole> roles=new ArrayList<UserRole>();

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getAuthorities() {
        return roles;
    }

    public void setAuthorities(List<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getUsername() {
        return getEmail();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public void eraseCredentials() {
        password = null;
    }
}
