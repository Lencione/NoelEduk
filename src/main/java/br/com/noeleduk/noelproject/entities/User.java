package br.com.noeleduk.noelproject.entities;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String email;
    private String password;
    private String token;
    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;
    private String cpf;
    private String rg;
    private String name;
    private String phone;
    private String role;
    private String document;
    private int edukoins;
    private String avatar;
    private int points;

    @ManyToMany(mappedBy = "students")
    private List<Class> classes;

    @ManyToMany
    @JoinTable(name = "users_lessons",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects;

}
