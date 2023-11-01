package id.cimbTest.controller;

import id.cimbTest.model.dto.JwtResponse;
import id.cimbTest.model.dto.LoginRequest;
import id.cimbTest.model.dto.RefreshTokenRequest;
import id.cimbTest.model.dto.SignupRequest;
import id.cimbTest.model.entity.Pengguna;
import id.cimbTest.security.jwt.JwtUtils;
import id.cimbTest.security.service.PenggunaDetailsImpl;
import id.cimbTest.security.service.UserDetailsServiceImpl;
import id.cimbTest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService penggunaService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest request) {
        log.info("request signin controller : {}", request);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        log.info("authentication : {}" , authentication);
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        String refreshToken = jwtUtils.generateRefresJwtToken(authentication);
        PenggunaDetailsImpl principal = (PenggunaDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok().body(new JwtResponse(token, refreshToken, principal.getUsername()));
    }

    @PostMapping("/signup")
    public ResponseEntity<Pengguna> signup(@RequestBody SignupRequest request) {
        Pengguna pengguna = new Pengguna();
        pengguna.setPassword(passwordEncoder.encode(request.getPassword()));
        pengguna.setUsername(request.getUsername());
        pengguna.setEmail(request.getEmail());
        pengguna.setIsAktif(true);
        pengguna.setRoles(request.getRoles());
        Pengguna created = penggunaService.create(pengguna);
        return ResponseEntity.ok().body(created);
    }


    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        String token = request.getRefreshToken();
        boolean valid = jwtUtils.validateJwtToken(token);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String username = jwtUtils.getUserNameFromJwtToken(token);
        PenggunaDetailsImpl penggunaDetailsImpl = (PenggunaDetailsImpl) userDetailsServiceImpl.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(penggunaDetailsImpl, null,
                penggunaDetailsImpl.getAuthorities());
        String newToken = jwtUtils.generateJwtToken(authentication);
        String refreshToken = jwtUtils.generateRefresJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(newToken, refreshToken, username));
    }
}
