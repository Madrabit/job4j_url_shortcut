package ru.job4j.url.controllers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.url.domain.Site;
import ru.job4j.url.services.SiteService;

import java.util.Optional;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final SiteService siteService;

    public RegistrationController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Site> create(@RequestBody Site site) {
        Optional<Site> newSite = this.siteService.findByName(site.getName());
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<Site> siteResponseEntity = new ResponseEntity<Site>(
                newSite.isEmpty() ? this.siteService.create(site.getName()) : newSite.get(),
                header,
                newSite.isEmpty() ? HttpStatus.CREATED : HttpStatus.FOUND
        );

        return siteResponseEntity;
    }
}
