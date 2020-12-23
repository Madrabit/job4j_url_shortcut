package ru.job4j.url.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.domain.Site;

import java.util.Optional;

public interface SiteRepository extends CrudRepository<Site, Integer> {
    Optional<Site> findByName(String name);
}
