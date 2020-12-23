package ru.job4j.url.services;

import org.springframework.stereotype.Service;
import ru.job4j.url.domain.Site;
import ru.job4j.url.repositories.SiteRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class SiteService {
    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public Optional<Site> findByName(String name) {
        return this.siteRepository.findByName(name);
    }

    public Site create(String name) {
        Site newSite = new Site(name);
        newSite.setLogin(randomGenerator());
        newSite.setPassword(randomGenerator());
        return this.siteRepository.save(newSite);
    }

    public String randomGenerator() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
