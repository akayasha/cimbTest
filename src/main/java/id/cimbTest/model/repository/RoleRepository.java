package id.cimbTest.model.repository;

import id.cimbTest.helper.ERole;
import id.cimbTest.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRole(ERole role);
}
