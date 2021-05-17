package kz.kaspi.kaspiproject.repositories;

import kz.kaspi.kaspiproject.entities.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Authors, Integer> {

}
