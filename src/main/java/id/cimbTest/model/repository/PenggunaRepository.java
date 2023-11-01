package id.cimbTest.model.repository;

import id.cimbTest.model.entity.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PenggunaRepository extends JpaRepository<Pengguna,Long> {
    Boolean existsByEmail(String email);

    Optional<Pengguna> findByUsername(String username);

    @Query(value = "SELECT * from Pengguna p where p.secure_id=?1", nativeQuery = true)
    Pengguna findbySecureId(String secureId);

    boolean existsByUsername(String username);
}
