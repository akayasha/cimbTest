package id.cimbTest.service;

import id.cimbTest.helper.ERole;
import id.cimbTest.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    public Role save(Role role);
    public Role findById(Long id);
    public List<Role> findAllRole();
    public void deleteById(Long id);
    public Role findByName(ERole eRole);
}
