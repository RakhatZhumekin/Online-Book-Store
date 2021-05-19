package kz.kaspi.kaspiproject.repositories;

import kz.kaspi.kaspiproject.entities.Sections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionsRepository extends JpaRepository<Sections, Integer> {

}
