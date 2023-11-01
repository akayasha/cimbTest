package id.cimbTest.service.impl;

import id.cimbTest.exception.BadRequestException;
import id.cimbTest.model.entity.Role;
import id.cimbTest.helper.ERole;
import id.cimbTest.model.repository.RoleRepository;
import id.cimbTest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepo;
    @Override
    public Role save(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public Role findById(Long id) {
        return roleRepo.findById(id).orElseThrow(() -> new BadRequestException("role is not found"));
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        roleRepo.deleteById(id);
    }

    @Override
    public Role findByName(ERole eRole) {
        return roleRepo.findByRole(eRole);
    }
//    @Override
//    public Object saveAll(Set<ERole> roles) {
//        Optional<Object> saveAll = roleRepo.saveAll(roles);
//        return saveAll;
//    }


}
