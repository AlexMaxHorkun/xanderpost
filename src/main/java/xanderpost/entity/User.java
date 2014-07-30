package xanderpost.entity;

import org.springframework.security.core.userdetails.UserDetails;
import xanderpost.security.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class User implements UserDetails{
    private long id;

    private String email;

    private String password;

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

    public Collection<GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
        auths.add(new GrantedAuthority());
        return auths;
    }

    public boolean isCredentialsNonExpired(){
        return true;
    }

    public String getUsername(){
        return getEmail();
    }

    public boolean isAccountNonExpired(){
        return true;
    }

    public boolean isAccountNonLocked(){
        return true;
    }

    public boolean isEnabled(){
        return true;
    }
}
