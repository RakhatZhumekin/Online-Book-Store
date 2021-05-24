package kz.kaspi.kaspiproject.repositories;

import kz.kaspi.kaspiproject.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    public Users findByNameIgnoreCase(String name);
}
