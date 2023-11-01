package id.cimbTest.security.service;

import id.cimbTest.model.entity.Pengguna;
import id.cimbTest.model.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PenggunaRepository penggunaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pengguna pengguna = Optional.of(penggunaRepository.findByUsername(username).get()).orElse(new Pengguna());
        return PenggunaDetailsImpl.build(pengguna);
    }
}
