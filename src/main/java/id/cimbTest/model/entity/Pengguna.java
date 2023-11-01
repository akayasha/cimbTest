package id.cimbTest.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Pengguna")
public class Pengguna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String username;
    private String email;
    private Boolean isAktif;
    @Column(name = "role")
    private String roles;
}