package url_shortcut.url_shortcut.controllers;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import url_shortcut.url_shortcut.domain.Site;
import url_shortcut.url_shortcut.repositories.SiteRepository;
import url_shortcut.url_shortcut.services.SiteService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final SiteService siteService;

    public RegistrationController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping("/")
    public ResponseEntity<Site> create(@RequestBody Site site) {
        Optional<Site> newSite = this.siteService.findByName(site.getName());
        return new ResponseEntity<Site>(
                newSite.orElse(this.siteService.create(site)),
                newSite.isPresent() ? HttpStatus.CREATED : HttpStatus.FOUND
        );
    }
}
