package id.cimbTest.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blogpost")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    private String author;
}
