package id.cimbTest.service;

import id.cimbTest.model.entity.Pengguna;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    Pengguna findById(String id);
    List<Pengguna> findAll();
    Pengguna create(Pengguna pengguna);

    Pengguna edit(Pengguna pengguna);

    void deleteById(Long id);
}
