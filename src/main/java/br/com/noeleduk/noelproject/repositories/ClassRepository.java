package br.com.noeleduk.noelproject.repositories;


import br.com.noeleduk.noelproject.entities.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity,UUID> {
  @Query("SELECT C FROM ClassEntity C WHERE C.id = ?1")
  ClassEntity findClassById(UUID id);
}
