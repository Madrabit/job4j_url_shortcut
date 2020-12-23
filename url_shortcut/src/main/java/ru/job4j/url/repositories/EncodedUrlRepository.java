package ru.job4j.url.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.domain.EncodedUrl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EncodedUrlRepository extends CrudRepository<EncodedUrl, Integer> {
    Optional<EncodedUrl> findByUrl(String url);

    Optional<EncodedUrl> findByCode(String code);

    List<EncodedUrl> findAll();
}
