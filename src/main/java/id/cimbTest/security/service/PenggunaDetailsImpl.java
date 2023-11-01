package id.cimbTest.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.cimbTest.model.entity.Pengguna;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class PenggunaDetailsImpl extends Pengguna implements UserDetails {

    private String username;

    @JsonIgnore
    private String password;



    public PenggunaDetailsImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public static PenggunaDetailsImpl build(Pengguna user){
        return new PenggunaDetailsImpl(
                user.getUsername(),
                user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
