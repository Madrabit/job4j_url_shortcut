package ru.job4j.url.services;

import org.springframework.stereotype.Service;
import ru.job4j.url.domain.EncodedUrl;
import ru.job4j.url.domain.Statistic;
import ru.job4j.url.repositories.EncodedUrlRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticService {
    private EncodedUrlRepository urlRepository;

    public StatisticService(EncodedUrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public List<Statistic> findAll() {
        List<Statistic> list;
        List<EncodedUrl> urls = urlRepository.findAll();
        return list = urls.stream()
                .map(url -> new Statistic(url.getUrl(), url.getFollowings()))
                .collect(Collectors.toList());
    }
}
