package ru.job4j.url.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.url.domain.EncodedUrl;
import ru.job4j.url.repositories.EncodedUrlRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Random;

@Service
public class EncodedUrlService {

    private final EncodedUrlRepository urlRepository;

    public EncodedUrlService(EncodedUrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Optional<EncodedUrl> findByUrl(String url) {
        return this.urlRepository.findByUrl(url);
    }

    public EncodedUrl create(String url) {
        EncodedUrl encodedUrl = new EncodedUrl(url);
        encodedUrl.setUrl(url);
        encodedUrl.setCode(randomGenerator());
        return this.urlRepository.save(encodedUrl);
    }

    public Optional<EncodedUrl> findByCode(String code) {
        return this.urlRepository.findByCode(code);
    }

    private String randomGenerator() {
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

    @Transactional
    public void update(int id)  {
        this.urlRepository.update(id);
    }
}
