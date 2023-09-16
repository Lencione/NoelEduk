package br.com.noeleduk.noelproject.repositories;

import br.com.noeleduk.noelproject.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {
  @Query("SELECT S FROM Subject S WHERE S.id = ?1 ")
  Subject findSubjectById(UUID subjectId);

}
