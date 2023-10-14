package br.com.noeleduk.noelproject.repositories;

import br.com.noeleduk.noelproject.dto.subjects.GetSubjectPresenceDto;
import br.com.noeleduk.noelproject.entities.SubjectEntity;
import br.com.noeleduk.noelproject.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, UUID> {
  @Query("SELECT S FROM SubjectEntity S WHERE S.id = ?1 ")
  SubjectEntity findSubjectById(UUID subjectId);

  List<SubjectEntity> findSubjectsByTeacher(UserEntity teacher);

@Query("SELECT s.name as subjectName, " +
        "(SELECT COUNT(l) FROM LessonEntity l WHERE l.subject = s) as total, " +
        "(SELECT COUNT(ul) FROM UserLessonEntity ul " +
        "JOIN ul.lesson l2 " +
        "JOIN l2.subject s3 " +
        "WHERE ul.user.id = ?1 AND s3 = s) as presences, " +
        "((SELECT COUNT(l) FROM LessonEntity l WHERE l.subject = s and DATE(l.date) < CURRENT_DATE ) - " +
        "(SELECT COUNT(ul) FROM UserLessonEntity ul " +
        "JOIN ul.lesson l2 " +
        "JOIN l2.subject s3 " +
        "WHERE ul.user.id =?1 AND s3 = s)) as faults " +
        "FROM SubjectEntity s " +
        "GROUP BY s.id, s.name")

  List<Object[]> findAllSubjectsByUserId(UUID student);
}
