package xanderpost.entity;

import org.springframework.security.core.userdetails.UserDetails;
import xanderpost.security.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class User implements UserDetails {
    private long id;

    private String email;

    private String password;

    private UserRole[] roles;

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

    public Collection<UserRole> getAuthorities() {
        return new ArrayList<UserRole>(Arrays.asList(roles));
    }

    public void setAuthorities(Collection<UserRole> roles) {
        this.roles = roles.toArray(new UserRole[roles.size()]);
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
