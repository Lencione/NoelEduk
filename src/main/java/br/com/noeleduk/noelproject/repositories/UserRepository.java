package br.com.noeleduk.noelproject.repositories;

import br.com.noeleduk.noelproject.entities.Lesson;
import br.com.noeleduk.noelproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  boolean existsByEmail(String email);

  boolean existsByDocument(String document);

  boolean existsByCpf(String cpf);

  boolean existsByRg(String rg);

  boolean existsByToken(String token);

  User findByToken(String token);

  @Query("SELECT u FROM User u WHERE u.email = ?1")
  User findByEmail(String email);


//STUDENTS

  @Query("SELECT U FROM User U WHERE U.role = 'student'")
  List<User> findAllStudents();

  @Query("SELECT U FROM User U WHERE U.role = 'student' AND U.email = ?1")
  User findStudentByEmail(String email);

  @Query("SELECT U FROM User U WHERE U.role = 'student' AND U.document = ?1")
  User findStudentByDocument(String document);

  @Query("SELECT L FROM Lesson L LEFT JOIN L.subject S LEFT JOIN S.classes C LEFT JOIN C.students U WHERE U = ?1")
  List<Lesson> getLessonsByUser(User user);

//TEACHERS

  @Query("SELECT u FROM User u WHERE u.role = 'teacher'")
  List<User> findAllTeachers();

  @Query("SELECT u FROM User u WHERE u.email = ?1 and u.role = 'teacher'")
  User findTeacherByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.token = ?1 and u.role = 'teacher'")
  User findTeacherByToken(String token);

  @Query("SELECT U FROM User U WHERE U.document = ?1 AND U.role = 'teacher'")
  User findTeacherByDocument(String document);
}
