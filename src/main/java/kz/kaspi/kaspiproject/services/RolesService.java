package kz.kaspi.kaspiproject.services;

import kz.kaspi.kaspiproject.entities.Roles;
import kz.kaspi.kaspiproject.repositories.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    private RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Roles findById(int id) {
        return rolesRepository.findById(id).orElse(null);
    }
}
