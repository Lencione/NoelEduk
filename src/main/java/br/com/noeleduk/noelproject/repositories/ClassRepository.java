package br.com.noeleduk.noelproject.repositories;


import br.com.noeleduk.noelproject.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<Class,UUID> {
  @Query("SELECT C FROM Class C WHERE C.id = ?1")
  Class findClassById(UUID id);
}
