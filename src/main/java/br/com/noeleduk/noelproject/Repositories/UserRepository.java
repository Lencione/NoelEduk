package br.com.noeleduk.noelproject.Repositories;

import br.com.noeleduk.noelproject.Entities.UserEntity;
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


//TEACHERS

  @Query("SELECT u FROM UserEntity u WHERE u.role = 'teacher'")
  List<UserEntity> findAllTeachers();

  @Query("SELECT u FROM UserEntity u WHERE u.email = ?1 and u.role = 'teacher'")
  UserEntity findTeacherByEmail(String email);

  @Query("SELECT u FROM UserEntity u WHERE u.token = ?1 and u.role = 'teacher'")
  UserEntity findTeacherByToken(String token);
}
