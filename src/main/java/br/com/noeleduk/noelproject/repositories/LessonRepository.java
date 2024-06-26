package br.com.noeleduk.noelproject.repositories;

import br.com.noeleduk.noelproject.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {

  LessonEntity findLessonById(UUID id);

  LessonEntity findLessonByToken(String token);
}
