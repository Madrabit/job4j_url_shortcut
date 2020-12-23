package ru.job4j.url.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url.domain.EncodedUrl;
import ru.job4j.url.services.EncodedUrlService;

import java.util.Optional;

@RestController
@RequestMapping("/convert")
public class ConverterController {
    private final EncodedUrlService converterService;

    public ConverterController(EncodedUrlService converterService) {
        this.converterService = converterService;
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<EncodedUrl> create(@RequestBody EncodedUrl url) {
        Optional<EncodedUrl> encodedUrl = this.converterService.findByUrl(url.getUrl());
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<> (
                encodedUrl.isEmpty() ? this.converterService.create(url.getUrl()) : encodedUrl.get(),
                header,
                encodedUrl.isEmpty() ? HttpStatus.CREATED : HttpStatus.FOUND
        );
    }
}
