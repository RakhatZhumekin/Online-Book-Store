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

    public void save(Sections section) {
        sectionsRepository.save(section);
    }

    public List<Sections> findAll() {
        return sectionsRepository.findAll();
    }

    public void deleteById(int id) {
        sectionsRepository.deleteById(id);
    }

    public Sections findByName(String name) { return sectionsRepository.findByNameIgnoreCase(name); }
}
