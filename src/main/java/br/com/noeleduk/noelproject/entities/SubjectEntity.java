package br.com.noeleduk.noelproject.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "subjects")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private int week_day;
    private LocalDate start_date;
    private LocalDate end_date;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private UserEntity teacher;

    @Column(name = "google_code")
    private String googleCode;

    @OneToMany(mappedBy = "subject")
    private List<LessonEntity> lessons;

    @ManyToMany
    @JoinTable(name = "classes_subjects",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    private List<ClassEntity> classes;
}
