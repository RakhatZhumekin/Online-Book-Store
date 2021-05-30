package kz.kaspi.kaspiproject.repositories;

import kz.kaspi.kaspiproject.entities.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors, Integer> {

    public Authors findByNameIgnoreCase(String name);
}
