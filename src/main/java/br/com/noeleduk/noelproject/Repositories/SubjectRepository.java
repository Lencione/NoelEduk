package br.com.noeleduk.noelproject.Repositories;

import br.com.noeleduk.noelproject.Entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, UUID> {
  @Query("SELECT S FROM SubjectEntity S WHERE S.id = ?1 ")
  SubjectEntity findSubjectById(UUID subjectId);

}
