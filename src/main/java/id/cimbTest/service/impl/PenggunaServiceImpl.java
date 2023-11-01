package id.cimbTest.service.impl;

import id.cimbTest.exception.BadRequestException;
import id.cimbTest.model.entity.Pengguna;
import id.cimbTest.model.repository.PenggunaRepository;
import id.cimbTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PenggunaServiceImpl implements UserService {
    @Autowired
    private PenggunaRepository penggunaRepository;

    @Override
    public Pengguna findById(String id) {
        return penggunaRepository.findByUsername(id).orElse(null);
    }

    @Override
    public List<Pengguna> findAll() {
        return penggunaRepository.findAll();
    }


    @Override
    public Pengguna create(Pengguna pengguna) {
        if (!StringUtils.hasText(pengguna.getUsername())) {
            throw new BadRequestException("Username harus diisi");
        }

        if (penggunaRepository.existsByUsername(pengguna.getUsername())) {
            throw new BadRequestException("Username " + pengguna.getUsername() + " sudah terdaftar");
        }

        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestException("Email harus diisi");
        }

        if (penggunaRepository.existsByEmail(pengguna.getEmail())) {
            throw new BadRequestException("Email " + pengguna.getEmail() + " sudah terdaftar");
        }

        pengguna.setIsAktif(true);
        return penggunaRepository.save(pengguna);
    }

    @Override
    public Pengguna edit(Pengguna pengguna) {
        if (!StringUtils.hasText(pengguna.getUsername())) {
            throw new BadRequestException("Username harus diisi");
        }

        if (!StringUtils.hasText(pengguna.getEmail())) {
            throw new BadRequestException("Email harus diisi");
        }

        return penggunaRepository.save(pengguna);
    }

    @Override
    public void deleteById(Long id) {
        penggunaRepository.deleteById(id);
    }
}
