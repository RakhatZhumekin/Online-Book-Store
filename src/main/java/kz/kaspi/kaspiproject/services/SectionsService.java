package kz.kaspi.kaspiproject.services;

import kz.kaspi.kaspiproject.entities.Sections;
import kz.kaspi.kaspiproject.repositories.SectionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionsService {

    private SectionsRepository sectionsRepository;

    public SectionsService(SectionsRepository sectionsRepository) {
        this.sectionsRepository = sectionsRepository;
    }

    public Sections findById(int id) {
        return sectionsRepository.findById(id).orElse(null);
    }

    public List<Sections> findAll() {
        return sectionsRepository.findAll();
    }
}
