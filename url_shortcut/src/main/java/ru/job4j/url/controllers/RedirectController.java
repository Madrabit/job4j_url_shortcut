package ru.job4j.url.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url.domain.EncodedUrl;
import ru.job4j.url.services.EncodedUrlService;

import java.util.Optional;

@RestController
@RequestMapping("/redirect/")
public class RedirectController {


    private final EncodedUrlService urlService;

    public RedirectController(EncodedUrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<String> redirect(@PathVariable String code) {
        Optional<EncodedUrl> encodedUrl = this.urlService.findByCode(code);
        String url = encodedUrl.get().getUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", url);
        urlService.update(encodedUrl.get().getId());
        return new ResponseEntity(
                headers,
                encodedUrl.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.FOUND
        );
    }

}
