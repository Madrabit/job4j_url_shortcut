package url_shortcut.url_shortcut;

import com.google.gson.Gson;
import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import url_shortcut.url_shortcut.controllers.RegistrationController;
import url_shortcut.url_shortcut.domain.Site;
import url_shortcut.url_shortcut.services.SiteService;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UrlShortcutApplication.class)
@AutoConfigureMockMvc
class RegistrationControlTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    @Autowired
    private SiteService siteService;

    @Test
    @WithMockUser
    void create() throws Exception {
        when(siteService.findByName("job4j.ru"))
                .thenReturn(Optional.of(new Site(
                        "job4j.ru")));
        mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"site\":\"job4j.ru\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"login\":null,\"password\":null,\"registered\":false}"))
                .andExpect(status().isFound());
    }
}
