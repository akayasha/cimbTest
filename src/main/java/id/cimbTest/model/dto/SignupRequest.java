package id.cimbTest.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private String roles;

}
