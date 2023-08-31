package br.com.noeleduk.noelproject.Repositories;

import br.com.noeleduk.noelproject.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
    boolean existsByDocument(String document);

    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
}
