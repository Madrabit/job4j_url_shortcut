package ru.job4j.url;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.job4j.url.domain.Site;
import ru.job4j.url.services.SiteService;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UrlShortcutApplication.class)
@AutoConfigureMockMvc
class RegistrationControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SiteService siteService;

    @Test
    @WithMockUser
    void create() throws Exception {

//        when(siteService.findByName("job4j.ru"))
//                .thenReturn(Optional.of(new Site(
//                        "job4j.ru")));


        mockMvc.perform(post("/registration/") // путь который тестим
                .contentType(MediaType.APPLICATION_JSON) // устанавливаем что тип JSON
                .content("{\"name\":\"job5j.ru\"}")) // это только ОДНО поле объекта, которое мне нужно, в реале их больше
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // проверяем что это Json
                .andDo(print()) // это вывести на печать результат
                .andExpect(status().isCreated()); // проверяет статус Response то есть если все ок то 200
    }

}
