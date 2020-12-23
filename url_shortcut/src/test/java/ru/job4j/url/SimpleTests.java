package ru.job4j.url;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.url.domain.Site;
import ru.job4j.url.services.SiteService;

@SpringBootTest(classes = UrlShortcutApplication.class)
public class SimpleTests {

    @Autowired
    SiteService siteService;

    @Test
    public void whenCreateThenReturnSite() {
        Site site = siteService.create("job4j.ru");
        Assert.assertEquals(site.getName(), "job4j.ru");
    }
}
