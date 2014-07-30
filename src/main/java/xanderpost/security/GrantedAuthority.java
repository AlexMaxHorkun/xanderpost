package xanderpost.security;

public class GrantedAuthority implements org.springframework.security.core.GrantedAuthority{
    public String getAuthority(){
        return "ROLE_USER";
    }
}
