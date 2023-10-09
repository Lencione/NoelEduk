package br.com.noeleduk.noelproject.repositories;


import br.com.noeleduk.noelproject.entities.UserLessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserLessonRepository extends JpaRepository<UserLessonEntity, UUID> {


}
