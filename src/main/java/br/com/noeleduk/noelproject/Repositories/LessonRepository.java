package br.com.noeleduk.noelproject.Repositories;

import br.com.noeleduk.noelproject.Entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {

}
