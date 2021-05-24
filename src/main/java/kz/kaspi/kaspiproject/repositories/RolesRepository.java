package kz.kaspi.kaspiproject.repositories;

import kz.kaspi.kaspiproject.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
