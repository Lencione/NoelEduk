package br.com.noeleduk.noelproject.entities;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "Lessons")
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDate date;
    private String description;
    private boolean active = true;
    private String justification;
    private boolean exam = false;
    private String token;
    private LocalDateTime token_expiration;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    @OneToMany(mappedBy = "lesson")
    private List<UserLessonEntity> studentsWithPresence;
}
