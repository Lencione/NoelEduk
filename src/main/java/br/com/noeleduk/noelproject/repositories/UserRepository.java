package br.com.noeleduk.noelproject.repositories;

import br.com.noeleduk.noelproject.entities.LessonEntity;
import br.com.noeleduk.noelproject.entities.SubjectEntity;
import br.com.noeleduk.noelproject.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
  boolean existsByEmail(String email);

  boolean existsByDocument(String document);

  boolean existsByCpf(String cpf);

  boolean existsByRg(String rg);

  boolean existsByToken(String token);

  UserEntity findByToken(String token);

  @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
  UserEntity findByEmail(String email);


//STUDENTS

  @Query("SELECT U FROM UserEntity U WHERE U.role = 'student'")
  List<UserEntity> findAllStudents();

  @Query("SELECT U FROM UserEntity U WHERE U.role = 'student' AND U.email = ?1")
  UserEntity findStudentByEmail(String email);

  @Query("SELECT U FROM UserEntity U WHERE U.role = 'student' AND U.document = ?1")
  UserEntity findStudentByDocument(String document);

  @Query("SELECT L FROM LessonEntity L LEFT JOIN L.subject S LEFT JOIN S.classes C LEFT JOIN C.students U WHERE U = ?1")
  List<LessonEntity> getLessonsByUser(UserEntity user);

//TEACHERS

  @Query("SELECT u FROM UserEntity u WHERE u.role = 'teacher'")
  List<UserEntity> findAllTeachers();

  @Query("SELECT u FROM UserEntity u WHERE u.email = ?1 and u.role = 'teacher'")
  UserEntity findTeacherByEmail(String email);

  @Query("SELECT u FROM UserEntity u WHERE u.token = ?1 and u.role = 'teacher'")
  UserEntity findTeacherByToken(String token);

  @Query("SELECT U FROM UserEntity U WHERE U.document = ?1 AND U.role = 'teacher'")
  UserEntity findTeacherByDocument(String document);

  @Query("SELECT U.user FROM UserLessonEntity U WHERE U.user.id = ?1 AND U.lesson.id = ?2 AND DATE(U.createdAt) = CURRENT_DATE")
  List<UserEntity> findUsersLessonToday(UUID id, UUID id1);
}
